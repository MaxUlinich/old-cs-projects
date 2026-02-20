import java.io.Serializable;

public class MyArrayList<E>  implements Serializable{
    private int size;
    private int capacity;
    private Object[] list;

    public MyArrayList() {
        size = 0;
        capacity = 10;
        list = new Object[capacity];
    }

    public void clear(){
        size = 0;
        capacity = 10;
        list = new Object[capacity];
    }
    public boolean add(E e) {
        if (size == capacity) {
            increaseListCapacity();
        }
        list[size] = e;
        size++;
        return true;
    }

    public void add(int index, E e) {
        if (size == capacity) {
            increaseListCapacity();
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = e;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) list[index];
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        E toRemove = (E) list[index];
        for (int i = index; i < size; i++) {
            try{
                list[i] = list[i + 1];
            }catch(IndexOutOfBoundsException ex){
                increaseListCapacity();
                list[i] = list[i + 1];
            }
        }
        list[size] = null;
        size--;
        return toRemove;
    }

    @SuppressWarnings("unchecked")
    public E remove(E e) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                Object removed = list[i];
                for (int j = i; j < size; j++) {
                    try{
                        list[j] = list[j + 1];
                    }catch(IndexOutOfBoundsException ex){
                        increaseListCapacity();
                        list[j] = list[j + 1];
                    }
                }
                list[size] = null;
                size--;
                return (E) removed;
            }
        }
        return null;
    }

    public void set(int index, E e) {
        list[index] = e;
    }

    public String toString() {
        if (size > 0) {
            String s = "";
            for (int i = 0; i < size - 1; i++) {
                s += list[i] + ", ";
            }
            s += list[size - 1];
            return s;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private void increaseListCapacity() {
        Object[] newList = new Object[capacity * 2];
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
        capacity *= 2;
    }
    
    public String getNumberedList(){
        String s="";
        for(int i = 0; i<size; i++){
            s+= (i+1) + " - " + list[i]+ "\n";
        }
        return s;
    }
}