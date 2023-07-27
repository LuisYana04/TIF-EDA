package Estructuras.LINKEDLIST;

import java.util.ArrayList;

public class Node<E> {
    private E data;
    private Integer conexiones;
    private ArrayList<Node <E> > next;

    public Node(E data) {
        this.data = data;
        this.before = null;
        this.next = null;
    }

    public E getData() {
        return data;
    }

    public Node<E> getBefore() {
        return before;
    }

    public void setBefore(Node<E> previous) {
        this.before = previous;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}