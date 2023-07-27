package Estructuras.LINKEDLIST;

//Sugerencia de estructura para relacionar los Almacenes

public class DoubleLinkedList <T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setBefore(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void remove(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                if (current == head) {
                    head = head.getNext();
                    if (head != null) {
                        head.setBefore(null);
                    }
                } else if (current == tail) {
                    tail = tail.getBefore();
                    if (tail != null) {
                        tail.setNext(null);
                    }
                } else {
                    Node<T> previous = current.getBefore();
                    Node<T> next = current.getNext();
                    previous.setNext(next);
                    next.setBefore(previous);
                }
                size--;
                return;
            }
            current = current.getNext();
        }
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("The list is empty.");
        }

        Node<T> removedNode = head;
        head = head.getNext();
        if (head != null) {
            head.setBefore(null);
        } else {
            tail = null;
        }

        removedNode.setNext(null);
        size--;

        return removedNode.getData();
    }
}