package cs.vsu.ru.treeMap_bidiMap;

public class TreeNode<T> implements Comparable {
    private T value;
    private boolean red;
    private TreeNode left, right, parent;

    public TreeNode(T value, boolean red, TreeNode left, TreeNode right, TreeNode parent) {
        this.value = value;
        this.red = red;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public TreeNode(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Object o) {
        return this.compareTo(o);
    }
}
