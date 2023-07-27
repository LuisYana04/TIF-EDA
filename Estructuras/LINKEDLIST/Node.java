package Estructuras.LINKEDLIST;

public class Node<E> {
    /* 
    data : Dato
    IC : Indice de Conexiones
    nextList : Referencia a los nodos
    */
    private E data;
    private Integer IC;

    public Node (E data, int connect){
        this.data = data;
        this.IC = connect;
    }

    public Node(E data) {
        this(data,0);
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
}