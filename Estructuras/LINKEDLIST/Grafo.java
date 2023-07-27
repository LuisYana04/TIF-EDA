package Estructuras.LINKEDLIST;

import java.util.ArrayList;

public class Grafo<T> {
    /*
        >> Lista de almacenes
        >> Matriz de Adyacencia para el peso de Arcos
     */
    private ArrayList<Node<T>> root;
    private ArrayList<ArrayList<Integer>> MA;

    public Grafo(ArrayList<Node<T>> root) {
        this.root = root;
        this.MA = new ArrayList<>();
        if(this.isEmpty())
        this.setMatrizAdyacencia(0);
        else
        this.setMatrizAdyacencia(this.root.size());
    }
    public Grafo(){
        this(null);
    }
    public void setMatrizAdyacencia(int length) {
        int numVertices = length;
        for (int i = 0; i < numVertices; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < numVertices; j++) {
                fila.add(0);
            }
            this.MA.add(fila);
        }
    }

    public void setRoot(ArrayList<Node<T>> root) {
        this.root = root;
    }

    public ArrayList<Node<T>> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(Node<T> next) {
        if (this.isEmpty()){
            this.root = new ArrayList<Node <T> >();
            this.add(next);
        }
        else {
            this.root.add(next);
        }
    }
    public void editPeso (double Peso){
        /* Vayan completando */
    }
}
