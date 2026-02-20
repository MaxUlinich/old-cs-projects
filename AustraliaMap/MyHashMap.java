public class MyHashMap<K, V> {
    private Object[] hasharray;
    private int size;
    private MyHashSet<K> keySet;

    public MyHashMap() {
        size = 0;
        hasharray = new Object[17576];
        keySet = new MyHashSet<K>();
    }

    public void clear() {
        size = 0;
        hasharray = new Object[50000];
        keySet = new MyHashSet<K>();
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {

        Object old = hasharray[key.hashCode()];
        hasharray[key.hashCode()] = value;
        // System.out.println(old);
        if (old == null) {
            // System.out.println("testing");
            size++;
        }
        keySet.add(key);

        return (V) old;
    }

    @SuppressWarnings("unchecked")
    public V get(Object o) {
        if (keySet.contains((K) o)) {
            return (V) hasharray[o.hashCode()];
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public V remove(Object o) {
        Object old = hasharray[o.hashCode()];
        hasharray[o.hashCode()] = null;
        if (old != null) {
            size--;
            keySet.remove((K) o);
        }
        return (V) old;
    }

    public int size() {
        return size;
    }

    public MyHashSet<K> keySet() {
        // System.out.println(keySet.toDLList().toString());
        return keySet;
    }
}