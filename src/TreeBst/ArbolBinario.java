package TreeBst;

import java.util.ArrayList;

import proyecto.Producto;

public class ArbolBinario<E extends Comparable<E>> {

    private NodoArbol<E> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void insertar(E data) {
        this.raiz = insertarRecursivo(this.raiz, data);
    }

    private NodoArbol<E> insertarRecursivo(NodoArbol<E> nodo, E data) {
        if (nodo == null) {
            return new NodoArbol<>(data);
        }

        if (data.compareTo(nodo.getData()) < 0) {
            nodo.setIzquierda(insertarRecursivo(nodo.getIzquierda(), data));
        } else if (data.compareTo(nodo.getData()) > 0) {
            nodo.setDerecha(insertarRecursivo(nodo.getDerecha(), data));
        }

        return nodo;
    }

    public E buscar(E data) {
        return buscarRecursivo(this.raiz, data);
    }
    
    private E buscarRecursivo(NodoArbol<E> nodo, E data) {
        if (nodo == null) {
            return null;
        }
    
        if (data.compareTo(nodo.getData()) == 0) {
            return nodo.getData();
        } else if (data.compareTo(nodo.getData()) < 0) {
            return buscarRecursivo(nodo.getIzquierda(), data);
        } else {
            return buscarRecursivo(nodo.getDerecha(), data);
        }
    }

    public E eliminar(E dato) {
        NodoArbol<E> nuevoNodoRaiz = new NodoArbol<>(null);
        eliminarNodo(this.raiz, dato, nuevoNodoRaiz);
        return nuevoNodoRaiz.getData();
    }
    
    private NodoArbol<E> eliminarNodo(NodoArbol<E> actual, E dato, NodoArbol<E> nuevoNodoRaiz) {
        if (actual == null) {
            return null;
        }
    
        int comparacion = dato.compareTo(actual.getData());
    
        if (comparacion < 0) {
            actual.setIzquierda(eliminarNodo(actual.getIzquierda(), dato, nuevoNodoRaiz));
        } else if (comparacion > 0) {
            actual.setDerecha(eliminarNodo(actual.getDerecha(), dato, nuevoNodoRaiz));
        } else {
            // El nodo a eliminar es el actual
    
            // Caso 1: El nodo tiene 0 o 1 hijo
            if (actual.getIzquierda() == null) {
                if (actual.getDerecha() == null) {
                    nuevoNodoRaiz.setData(actual.getData());  // Guardar el nodo que será eliminado
                }
                return actual.getDerecha();
            } else if (actual.getDerecha() == null) {
                nuevoNodoRaiz.setData(actual.getData());  // Guardar el nodo que será eliminado
                return actual.getIzquierda();
            }
    
            // Caso 2: El nodo tiene 2 hijos
            nuevoNodoRaiz.setData(actual.getData());  // Guardar el nodo que será eliminado
            actual.setData(encontrarMinimo(actual.getDerecha()));
            actual.setDerecha(eliminarNodo(actual.getDerecha(), actual.getData(), nuevoNodoRaiz));
        }
    
        return actual;
    }

    private E encontrarMinimo(NodoArbol<E> actual) {
        while (actual.getIzquierda() != null) {
            actual = actual.getIzquierda();
        }
        return actual.getData();
    }

    // Método para realizar el recorrido inorden del árbol
    public void recorridoInorden() {
        recorridoInorden(raiz);
    }

    private void recorridoInorden(NodoArbol<E> actual) {
        if (actual != null) {
            recorridoInorden(actual.getIzquierda());
            System.out.println(actual.getData());
            recorridoInorden(actual.getDerecha());
        }
    }

    public int getStockTotal() {
        return getStockTotalRecursivo(this.getRaiz());
    }

    private int getStockTotalRecursivo(NodoArbol<E> nodo) {
        if (nodo == null) {
            return 0;
        }
        Producto producto = (Producto) nodo.getData();
        return producto.getStock() + getStockTotalRecursivo(nodo.getIzquierda()) + getStockTotalRecursivo(nodo.getDerecha());
    }

    public ArrayList<E> getProductos() {
        ArrayList<E> productos = new ArrayList<>();
        getProductosRecursivo(this.raiz, productos);
        return productos;
    }

    private void getProductosRecursivo(NodoArbol<E> nodo, ArrayList<E> productos) {
        if (nodo != null) {
            productos.add(nodo.getData());
            getProductosRecursivo(nodo.getIzquierda(), productos);
            getProductosRecursivo(nodo.getDerecha(), productos);
        }
    }

    public NodoArbol<E> getRaiz() {
        return raiz;
    }
}
