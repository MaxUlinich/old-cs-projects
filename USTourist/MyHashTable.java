public class MyHashTable<K, V> {
    private DLList<V>[] table;
    private MyHashSet<K> keySet;

    public MyHashTable() {
        table = new DLList[1000000];
        keySet = new MyHashSet();
    }

    public void put(K key, V value) {
        if (!keySet.contains(key)) {
            keySet.add(key);
            table[key.hashCode()] = new DLList();
        }
        table[key.hashCode()].add(value);
    }

    public DLList<V> get(K key) {
        return table[key.hashCode()];
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    public String toString() {
        String s = "";
        DLList<K> list = keySet.toDLList();
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i).hashCode() + " - " + list.get(i).toString() + " - "
                    + table[list.get(i).hashCode()].toString() + "\n";

        }
        return s;
    }

    public void remove(K key, V value) {
        table[key.hashCode()].remove(value);
        if (table[key.hashCode()].size() == 0) {
            table[key.hashCode()] = null;
            keySet.remove(key);
        }
    }

    public void remove(K key) {
        table[key.hashCode()] = null;
        keySet.remove(key);
    }

}
