package graphlink;



import java.util.ArrayList;
import java.util.List;

import listlinked.ListLinked;
import listlinked.Node;

public class Vertex<E> implements Comparable<Vertex<E>> {
    protected E data;
    protected ListLinked<Edge<E>> listAdj;
    protected int dist;
    protected boolean visited;
    private Vertex<E> prev;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new ListLinked<Edge<E>>();
        this.dist = Integer.MAX_VALUE;
        this.visited = false;
    }

    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.data + " -->\t " + this.listAdj.toString() + "\n";
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
    public Vertex<E> getPrev() {
        return prev;
    }

    public void setPrev(Vertex<E> prev) {
        this.prev = prev;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public int compareTo(Vertex<E> other) {
        return Integer.compare(this.getDist(), other.getDist());
    }
    public Iterable<Edge<E>> getEdges() {
        List<Edge<E>> edges = new ArrayList<>();
        Node<Edge<E>> node = this.listAdj.getHead();
        while (node != null) {
            edges.add(node.getData());
            node = node.getNext();
        }
        return edges;
    }
}