package cs.vsu.ru.treeMap_bidiMap;

public class TreeMap<K extends Comparable<K>, V> {

    private final RBTree tree = new RBTree();

    public TreeMap() {
    }

    public RBTree getTree() {
        return tree;
    }

    public void put(K key, V value){
        tree.put(new Entry(key, value));
    }

    public V getValue(K key){
        return (V) (((Entry) tree.getNode(tree.getRoot(), new Entry(key, null)).getValue())).getValue();
    }

    public V remove(K key){
        return (V) ((Entry) tree.remove(new Entry(key, null))).getValue();
    }

    public boolean containsKey(K key){
        if (tree.getNode(tree.getRoot(), new Entry(key, null)) != null){
            return true;
        }
        return false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder(" ");
        if (tree.size() == 0) {
            return "Map is empty";
        }
        else {
            Entry entry = (Entry) tree.getRoot().getValue();
            for (Object node :
                    tree) {
                str.append("Key: ").append(((Entry) ((TreeNode) node).getValue()).getKey().toString())
                        .append(" ; Value: ").append(((Entry) ((TreeNode) node).getValue()).getValue().toString())
                        .append("\n");
            }
            return String.valueOf(str);
        }
    }


}
