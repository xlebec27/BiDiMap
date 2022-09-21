package cs.vsu.ru.treeMap_bidiMap;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RBTree<T extends Comparable> implements Iterable {

    static final boolean RED = true;
    static final boolean BLACK = false;
    private TreeNode root = null;
    private int size = 0;

    public TreeNode getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public void put(T value) {
        TreeNode newNode = new TreeNode(value);
        if (root == null) {
            setRoot(newNode);
            size++;
            return;
        }
        TreeNode node = root;
        while (true) {
            int cmp = value.compareTo(node.getValue());
            if (cmp < 0) {
                if (node.getLeft() == null) {
                    setLeft(node, newNode);
                    size++;
                    correctAfterAdd(node.getLeft());
                    return;
                }
                node = node.getLeft();
            } else if (cmp > 0) {
                if (node.getRight() == null) {
                    setRight(node, newNode);
                    size++;
                    correctAfterAdd(node.getRight());
                    return;
                }
                node = node.getRight();
            }
            else if (cmp == 0){
                node.setValue(value);
                return;
            }
        }
    }

    public TreeNode getNode(TreeNode<T> node1, T value) {
        if (node1 == null) {
            return null;
        }
        int cmp = node1.getValue().compareTo(value);
        if (cmp == 0) {
            return node1;
        } else if (cmp > 0) {
            return getNode(node1.getLeft(), value);
        } else {
            return getNode(node1.getRight(), value);
        }
    }

    public TreeNode getMinNode(TreeNode node) {
        return (node == null || node.getLeft() == null) ? node : getMinNode(node.getLeft());
    }

    public T remove(T value) {
        TreeNode node = getNode(getRoot(), value);
        if (node == null){
            return null;
        }
        T oldValue = (T) node.getValue();
        if (node.getLeft() != null && node.getRight() != null) {
            TreeNode nextValueNode = getMinNode(node.getRight());
            node.setValue(nextValueNode.getValue());
            node = nextValueNode;
        }
        TreeNode child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        if (child != null) {
            if (node == root) {
                setRoot(child);
                root.setRed(BLACK);
            } else if (node.getParent().getLeft() == node) {

                setLeft(node.getParent(), child);
            } else {
                setRight(node.getParent(), child);
            }
            if (node.isRed() == BLACK) {
                correctAfterRemove(child);
            }
        } else if (node == root) {
            root = null;
        } else {
            if (node.isRed() == BLACK) {
                correctAfterRemove(node);
            }
            removeFromParent(node);
        }
        size--;
        return oldValue;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    private void correctAfterAdd(TreeNode node) {
        if (node != null) {
            node.setRed(RED);
        }
        if (node != null && node != root && colorOf(node.getParent()) == RED) {
            if (isRed(siblingOf(parentOf(node)))) {
                setColor(parentOf(node), BLACK);
                setColor(siblingOf(parentOf(node)), BLACK);
                setColor(grandparentOf(node), RED);
                correctAfterAdd(grandparentOf(node));
            }
            else if (parentOf(node) == leftOf(grandparentOf(node))) {
                if (node == rightOf(parentOf(node))) {
                    leftRotate(node = parentOf(node));
                }
                setColor(parentOf(node), BLACK);
                setColor(grandparentOf(node), RED);
                rightRotate(grandparentOf(node));
            }
            else if (parentOf(node) == rightOf(grandparentOf(node))) {
                if (node == leftOf(parentOf(node))) {
                    rightRotate(node = parentOf(node));
                }
                setColor(parentOf(node), BLACK);
                setColor(grandparentOf(node), RED);
                leftRotate(grandparentOf(node));
            }
        }
        setColor(root, BLACK);
    }

    private void correctAfterRemove(TreeNode node) {
        while (node != root && isBlack(node)) {
            if (node == leftOf(parentOf(node))) {
                TreeNode sibling = rightOf(parentOf(node));
                if (isRed(sibling)) {
                    setColor(sibling, BLACK);
                    setColor(parentOf(node), RED);
                    leftRotate(parentOf(node));
                    sibling = rightOf(parentOf(node));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, RED);
                    node = parentOf(node);
                } else {
                    if (isBlack(rightOf(sibling))) {
                        setColor(leftOf(sibling), BLACK);
                        setColor(sibling, RED);
                        rightRotate(sibling);
                        sibling = rightOf(parentOf(node));
                    }
                    setColor(sibling, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(rightOf(sibling), BLACK);
                    leftRotate(parentOf(node));
                    node = root;
                }
            } else {
                TreeNode sibling = leftOf(parentOf(node));
                if (isRed(sibling)) {
                    setColor(sibling, BLACK);
                    setColor(parentOf(node), RED);
                    rightRotate(parentOf(node));
                    sibling = leftOf(parentOf(node));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, RED);
                    node = parentOf(node);
                } else {
                    if (isBlack(leftOf(sibling))) {
                        setColor(rightOf(sibling), BLACK);
                        setColor(sibling, RED);
                        leftRotate(sibling);
                        sibling = leftOf(parentOf(node));
                    }
                    setColor(sibling, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(leftOf(sibling), BLACK);
                    rightRotate(parentOf(node));
                    node = root;
                }
            }
        }
        setColor(node, BLACK);
    }

    private void leftRotate(TreeNode node) {
        if (node.getRight() == null) {
            return;
        }
        TreeNode right = node.getRight();
        setRight(node, right.getLeft());
        if (node.getParent() == null) {
            setRoot(right);
        } else if (node.getParent().getLeft() == node) {
            setLeft(node.getParent(), right);
        } else {
            setRight(node.getParent(), right);
        }
        setLeft(right, node);
    }

    private void rightRotate(TreeNode node) {
        if (node.getLeft() == null) {
            return;
        }
        TreeNode left = node.getLeft();
        setLeft(node, left.getRight());
        if (node.getParent() == null) {
            setRoot(left);
        } else if (node.getParent().getLeft() == node) {
            setLeft(node.getParent(), left);
        } else {
            setRight(node.getParent(), left);
        }
        setRight(left, node);
    }

    private void removeFromParent(TreeNode node) {
        if (node.getParent() != null) {
            if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(null);
            } else if (node.getParent().getRight() == node) {
                node.getParent().setRight(null);
            }
            node.setParent(null);
        }
    }

    private boolean colorOf(TreeNode node) {
        return node == null ? BLACK : node.isRed();
    }

    private boolean isRed(TreeNode node) {
        return colorOf(node) == RED;
    }

    private boolean isBlack(TreeNode node) {
        return colorOf(node) == BLACK;
    }

    private void setColor(TreeNode node, boolean red) {
        if (node != null) {
            node.setRed(red);
        }
    }

    private void setLeft(TreeNode node, TreeNode left) {
        if (node != null) {
            node.setLeft(left);
            if (left != null) {
                left.setParent(node);
            }
        }
    }

    private void setRight(TreeNode node, TreeNode right) {
        if (node != null) {
            node.setRight(right);
            if (right != null) {
                right.setParent(node);
            }
        }
    }

    private void setRoot(TreeNode node) {
        root = node;
        if (node != null) {
            node.setParent(null);
        }
    }

    private TreeNode parentOf(TreeNode node) {
        return node == null ? null : node.getParent();
    }

    private TreeNode grandparentOf(TreeNode node) {
        // return parentOf(parentOf(node));
        return (node == null || node.getParent() == null) ? null : node.getParent().getParent();
    }

    private TreeNode siblingOf(TreeNode node) {
        return (node == null || node.getParent() == null)
                ? null
                : (node == node.getParent().getLeft())
                ? node.getParent().getRight() : node.getParent().getLeft();
    }

    private TreeNode leftOf(TreeNode node) {
        return node == null ? null : node.getLeft();
    }

    private TreeNode rightOf(TreeNode node) {
        return node == null ? null : node.getRight();
    }

    private int getHeight(TreeNode node) {
        return (node == null) ? -1 : Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
    }

    public int getHeight() {
        return getHeight(root);
    }

    @Override
    public Iterator iterator() {
        return new TreeIterator(getRoot());
    }

    class TreeIterator implements Iterator {

        private TreeNode next;

        public TreeIterator(TreeNode root) {
            next = root;
            if (next == null)
                return;

            while (next.getLeft() != null)
                next = next.getLeft();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {

            if (!hasNext()) throw new NoSuchElementException();
            TreeNode r = next;
            if (next.getRight() != null) {
                next = next.getRight();
                while (next.getLeft() != null)
                    next = next.getLeft();
                return r;
            }

            while (true) {
                if (next.getParent() == null) {
                    next = null;
                    return r;
                }
                if (next.getParent().getLeft() == next) {
                    next = next.getParent();
                    return r;
                }
                next = next.getParent();
            }
        }
    }
}

