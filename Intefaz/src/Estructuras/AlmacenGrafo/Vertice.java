package Estructuras.AlmacenGrafo;
import Estructuras.LINKEDLIST.LinkedList;
public class Vertice <A> {
    protected A Almacen;
    protected LinkedList<Arista <A>> conect;
    
	public Vertice(A Almacen) {
		this.Almacen = Almacen;
		this.conect = new LinkedList<Arista<A>>();
	}
    public void setAlmacen(A almacen) {
        Almacen = almacen;
    }
    public A getAlmacen() {
        return Almacen;
    }
    public void setConect(LinkedList<Arista<A> >conect) {
        this.conect = conect;
    }
    public LinkedList<Arista<A> >getConect() {
        return conect;
    }
    public boolean equals(Object o) {
		if (o instanceof Vertice<?>) {
			Vertice<A> v = (Vertice<A>) o;
			return this.Almacen.equals(v.Almacen);
		}
		return false;
	}
    @Override
	public String toString() {
		return this.Almacen + " -->\t " + this.conect.toString() + "\n";
	}
}
