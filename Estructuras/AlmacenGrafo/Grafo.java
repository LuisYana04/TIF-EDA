package Estructuras.AlmacenGrafo;

import  Estructuras.LINKEDLIST.LinkedList;

public class Grafo <A> {
    /*
        >> Lista de almacenes --> Vertices
     */
    private LinkedList< Vertice <A> > Graph;

    public Grafo(LinkedList <Vertice <A> > root) {
        this.Graph = root;
    }
    public Grafo(){
        this(null);
    }

    public void setRoot(LinkedList<Vertice <A>> root) {
        this.Graph = root;
    }

    public LinkedList<Vertice<A>> getRoot() {
        return Graph;
    }

    public boolean isEmpty() {
        return Graph == null;
    }    
}