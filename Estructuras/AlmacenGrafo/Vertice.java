package Estructuras.AlmacenGrafo;

public class Vertice <A> {
    private A Almacen;
    private Arista <A> conect;

    public Vertice (A newAlmacen, Arista <A> enlaces) {
        this.Almacen = newAlmacen;
        this.conect = enlaces;
    }
    
    public Vertice (A newA) {
        this(newA, null);
    }
    public Vertice () {
        this(null, null);
    }
    public void setAlmacen(A almacen) {
        Almacen = almacen;
    }
    public A getAlmacen() {
        return Almacen;
    }
    public void setConect(Arista<A> conect) {
        this.conect = conect;
    }
    public Arista<A> getConect() {
        return conect;
    }
}
