package Estructuras.LINKEDLIST;

public class Node<A> {
    /* 
    data : Dato
    IC : Indice de Conexiones
    nextList : Referencia a los nodos
    */
    private A data;
    private Node <A> nextNode;

    public Node (A data, Node <A> NewNode){
        this.data = data;
        this.nextNode = NewNode;
    }

    public Node(A data) {
        this(data,null);
    }

    public Node () {
        this(null, null);
    }

    public void setData(A data) {
        this.data = data;
    }
    public A getData() {
        return data;
    }
    public void setNextNode(Node<A> nextNode) {
        this.nextNode = nextNode;
    }
    public Node<A> getNextNode() {
        return nextNode;
    }
    @Override
    public String toString (){
        return this.data.toString();
    }
}