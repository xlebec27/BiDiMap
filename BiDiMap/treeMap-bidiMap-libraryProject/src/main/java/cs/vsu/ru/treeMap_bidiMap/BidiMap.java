package cs.vsu.ru.treeMap_bidiMap;

public class BidiMap<K extends Comparable<K>, V extends Comparable<V>>{

    private final RBTree keysTree  = new RBTree();
    private final RBTree valuesTree = new RBTree();

    public BidiMap(){
    }

    public RBTree getKeysTree() {
        return keysTree;
    }

    public RBTree getValuesTree() {
        return valuesTree;
    }

    public void put(K key, V value){
        if (containsKey(key)) {
            V oldValue = (V) ((Entry) keysTree.getNode(keysTree.getRoot(), new Entry(key, null)).getValue()).getValue();
            valuesTree.remove(new Entry(oldValue, null));
        }
        if (containsValue(value)){
            K oldKey = (K) ((Entry) valuesTree.getNode(valuesTree.getRoot(), new Entry(value, null)).getValue()).getValue();
            keysTree.remove(new Entry(oldKey, null));
        }
        keysTree.put(new Entry(key, value));
        valuesTree.put(new Entry(value, key));
    }

    public K getKey(V value){
        return (K) (((Entry) valuesTree.getNode(valuesTree.getRoot(), new Entry(value, null)).getValue())).getValue();
    }

    public V getValue(K key){
        return (V) (((Entry) keysTree.getNode(keysTree.getRoot(), new Entry(key, null)).getValue())).getValue();
    }

    public V removeByKey(K key){
        V value = (V) ((Entry) keysTree.remove(new Entry(key, null))).getValue();
        valuesTree.remove(new Entry(value, null));
        return value;
    }

    public K removeByValue(V value){
        K key = (K) ((Entry) valuesTree.remove(new Entry(value, null))).getValue();
        keysTree.remove(new Entry(key, null));
        return key;
    }

    public boolean containsKey(K key){
        if (keysTree.getNode(keysTree.getRoot(), new Entry(key, null)) != null){
            return true;
        }
        return false;
    }

    public boolean containsValue(V value){
        if (valuesTree.getNode(valuesTree.getRoot(), new Entry(value, null)) != null){
            return true;
        }
        return false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder(" ");
        if (keysTree.size() == 0) {
            return "Map is empty";
        }
        else {
            Entry entry = (Entry) keysTree.getRoot().getValue();
            for (Object node :
                    keysTree) {
                str.append("Key: ").append(((Entry) ((TreeNode) node).getValue()).getKey().toString())
                        .append(" ; Value: ").append(((Entry) ((TreeNode) node).getValue()).getValue().toString())
                        .append("\n");
            }
            return String.valueOf(str);
        }
    }
}
