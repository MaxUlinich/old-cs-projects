public class DLList<E> {
    private Node<E> head, tail;
    private int size;

    public DLList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public boolean add(E e) {
        Node<E> node = new Node<>(e);
        node.setPrev(tail.prev());
        node.setNext(tail);
        tail.prev().setNext(node);
        tail.setPrev(node);
        size++;
        return true;
    }

    public void add(int index, E e) {
        if (size == 0) {
            add(e);
        } else {
            Node<E> node = getNode(index);
            Node<E> newNode = new Node<>(e);
            newNode.setPrev(node.prev());
            newNode.setNext(node);
            node.prev().setNext(newNode);
            node.setPrev(newNode);
            size++;
        }
    }

    public E get(int index) {
        return getNode(index).get();
    }

    public E remove(int index) {
        Node<E> node = getNode(index);
        node.prev().setNext(node.next());
        node.next().setPrev(node.prev());
        size--;
        return node.get();
    }

    public boolean remove(Object o) {
        Node<E> loop = head;
        while (loop.next().get() != null) {
            loop = loop.next();
            if ((loop.get()).equals(o)) {
                loop.prev().setNext(loop.next());
                loop.next().setPrev(loop.prev());
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        Node<E> loop = head;
        while (loop.next().get() != null) {
            loop = loop.next();
            if ((loop.get()).equals(o)) {
                return true;
            }

        }
        return false;
    }

    public E set(int index, E e) {
        Node<E> replace = getNode(index);
        E replaced = replace.get();
        replace.set(e);
        return replaced;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> loop;
        if (index > size / 2) {
            loop = tail;
            for (int i = size - 1; i >= index; i--) {
                loop = loop.prev();
            }
        } else {
            loop = head;
            for (int i = 0; i <= index; i++) {
                loop = loop.next();
            }
        }
        return loop;
    }

    public String toString() {
        String s = "[";
        Node<E> current = head;
        while (current.next().get() != null) {
            current = current.next();
            s += current.get();
            if (current.next().get() != null) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
