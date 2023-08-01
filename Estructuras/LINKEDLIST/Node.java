package Estructuras.LINKEDLIST;

public class Node<E> {
    /* 
    data : Dato
    IC : Indice de Conexiones
    nextList : Referencia a los nodos
    */
    private E data;
    private Node <E> nextNode;

    public Node (E data, Node <E> NewNode){
        this.data = data;
        this.nextNode = NewNode;
    }

    public Node(E data) {
        this(data,null);
    }

    public Node () {
        this(null, null);
    }

    public void setData(E data) {
        this.data = data;
    }
    public E getData() {
        return data;
    }
    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }
    public Node<E> getNextNode() {
        return nextNode;
    }
    @Override
    public String toString (){
        return this.data.toString();
    }
}