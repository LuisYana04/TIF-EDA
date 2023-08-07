package PriorityQueue;



import java.util.Comparator;

import listlinked.Node;

public class PriorityQueue<E extends Comparable<E>> {
    private Node<E> head;
    private Comparator<E> comparator;
    
    public PriorityQueue() {
        this.head = null;
    }

    public PriorityQueue(Comparator<E> comparator) {
        this.head = null;
        this.comparator = comparator; // Asigna el comparador proporcionado
    }

    public E removeMin() {
        if (isEmpty()) {
            return null;
        } else {
            Node<E> minNode = head;
            head = head.getNext();
            minNode.setNext(null);
            return minNode.getData();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
    public void offer(E data) {
        if (isEmpty() || data.compareTo(head.getData()) < 0) {
            head = new Node<E>(data, head);
        } else {
            Node<E> tmp = head;
            while (tmp.getNext() != null && data.compareTo(tmp.getNext().getData()) >= 0) {
                tmp = tmp.getNext();
            }
            Node<E> newNode = new Node<E>(data, tmp.getNext());
            tmp.setNext(newNode);
        }
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        } else {
            Node<E> minNode = head;
            head = head.getNext();
            minNode.setNext(null);
            return minNode.getData();
        }
    }
    @Override
    public String toString() {
        String str = "";
        for(Node<E> aux = this.head; aux != null; aux = aux.getNext()) { 
            str = str + aux.toString() + ", "; 
        }
        return str;
    }
}

