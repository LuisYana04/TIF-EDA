package TreeBst;

public class NodoArbol<E> {
    private E data;
    private NodoArbol<E> izquierda;
    private NodoArbol<E> derecha;

    public NodoArbol(E data) {
        this.data = data;
        this.izquierda = null;
        this.derecha = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public NodoArbol<E> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoArbol<E> izquierda) {
        this.izquierda = izquierda;
    }

    public NodoArbol<E> getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoArbol<E> derecha) {
        this.derecha = derecha;
    }
}
