package Estructuras.LINKEDLIST;

public class Grafo <T>{
    private Node <T> root;
    public Grafo (Node <T> root) {
        this.root = root;
    }
    public void setRoot(Node<T> root) {
        this.root = root;
    }
    public Node<T> getRoot() {
        return root;
    }
    public boolean isEmpty (){
        if(this.root == null)
            return true;
        return false;
    }
    //public boolen HYM () {
    //    return null;
    //}
    public void add (Node <T> next){
        if(this.isEmpty())
            this.root = next;
        else{
            
        }
    }
}
