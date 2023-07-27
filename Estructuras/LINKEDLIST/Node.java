package Estructuras.LINKEDLIST;

public class Node<E> {
    /* 
    data : Dato
    IC : Indice de Conexiones
    nextList : Referencia a los nodos
    */
    private E data;

    public Node (E data, int connect){
        this.data = data;
    }

    public Node(E data) {
        this(data,0);
    }

    public void setData(E data) {
        this.data = data;
    }
    public E getData() {
        return data;
    }
}