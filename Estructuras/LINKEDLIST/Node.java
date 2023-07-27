package Estructuras.LINKEDLIST;

import java.util.ArrayList;

public class Node<E> {
    /* 
    data : Dato
    IC : Indice de Conexiones
    nextList : Referencia a los nodos
    */
    private E data;
    private Integer IC;
    private ArrayList<Node <E> > nextList;

    public Node (E data, int connect, ArrayList <Node <E> > next){
        this.data = data;
        this.IC = connect;
        this.nextList = next;
    }

    public Node(E data) {
        this(data,0,null);
    }

    public void setConexiones(Integer IC) {
    this.IC = IC;
    }

    public Integer getConexiones() {
        return IC;
    }

    public void setData(E data) {
        this.data = data;
    }
    public E getData() {
        return data;
    }

    public void setNextList(ArrayList<Node<E>> nextList) {
        this.nextList = nextList;
    }

    public ArrayList<Node<E>> getNextList() {
        return nextList;
    }
}