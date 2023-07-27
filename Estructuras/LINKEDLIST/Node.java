package Estructuras.LINKEDLIST;

public class Node<E> {
    /* 
    data : Dato
    IC : Indice de Conexiones
    nextList : Referencia a los nodos
    */
    private E data;
    private double addressX;
    private double addressY;

    public Node (E data, double x, double y){
        this.data = data;
        this.addressX = x;
        this.addressY = y;
    }

    public Node(E data) {
        this(data,0,0);
    }

    public void setData(E data) {
        this.data = data;
    }
    public E getData() {
        return data;
    }
    public void setAddressX(double addressX) {
        this.addressX = addressX;
    }
    public double getAddressX() {
        return addressX;
    }
    public void setAddressY(double addressY) {
        this.addressY = addressY;
    }
    public double getAddressY() {
        return addressY;
    }
}