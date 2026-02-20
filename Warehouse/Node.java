public class Node<E> {
    private E data;
    private Node<E> next, previous;

    public Node(E data) {
        this.data = data;
        next = null;
        previous = null;
    }

    public E get() {
        return data;
    }
    public void set(E e){
        data= e;
    }
    public Node<E> next() {
        return next;
    }

    public Node<E> prev() {
        return previous;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrev(Node<E> prev) {
        previous = prev;
    }
}