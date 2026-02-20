public class MyHashSet<E> {
    private Object[] hashArray;
    private DLList<E> dllist = new DLList<E>();
    private int size;
    private int scale = 1000000;

    public MyHashSet() {
        hashArray = new Object[scale];
        size = 0;
    }

    public boolean add(E item) {
        int location = item.hashCode();
        if (hashArray[location] == null) {
            hashArray[location] = item;
            dllist.add(item);
            size++;
            return true;
        } else {
            remove(item);
            add(item);
        }

        return false;
    }

    public void clear() {

        dllist.clear();
        hashArray = new Object[scale];
        size = 0;
    }

    public boolean contains(E item) {
        if (hashArray[item.hashCode()] != null && item.equals(hashArray[item.hashCode()])) {
            return true;
        }
        return false;
    }

    public boolean remove(E item) {
        if (hashArray[item.hashCode()] != null && item.equals(hashArray[item.hashCode()])) {
            hashArray[item.hashCode()] = null;
            dllist.remove(item);
            size--;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public DLList<E> toDLList() {
        return dllist;
    }

}
