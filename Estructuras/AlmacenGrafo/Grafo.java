package Estructuras.AlmacenGrafo;

import  Estructuras.LINKEDLIST.LinkedList;
import  Estructuras.LINKEDLIST.Node;

public class Grafo <A> {
    /*
        >> Lista de almacenes --> Vertices
     */
    protected LinkedList< Vertice <A> > Graph;

    public Grafo(){
        this.Graph = new LinkedList<Vertice <A> >();
    }

    public LinkedList<Vertice<A>> getRoot() {
        return Graph;
    }

    public boolean isEmpty() {
        return Graph == null;
    }    
    public void insertVertice(A data) {
		Vertice<A> v = new Vertice<A>(data);
		if (this.Graph.search(v))
			System.out.println("Vertice con "+ data +" ya fue insertado");
		else
			this.Graph.insertFirst(v);
	}
	
	public void insertArista(A dataOri, A dataDes) {  
		Vertice<A> vOri = this.Graph.searchData(new Vertice <A>(dataOri));
		Vertice<A> vDes = this.Graph.searchData(new Vertice <A>(dataDes));
		if (vOri == null || vDes == null)
			System.out.println(dataOri + " o " + dataDes + " no existen ....");
		else{
			Arista<A> e = new Arista<A>(vDes);
			if (vOri.conect.search(e))
				System.out.println("Arista ("+ dataOri + "," + dataDes + ") ya fue insertada ...");
			else {
				vOri.conect.insertFirst(e);
				vDes.conect.insertFirst(new Arista<A>(vOri));
			}
		}
	} 
	
	@Override
	public String toString() {
		return this.Graph.toString();
	}
	
    public void removeEdge(A dataOri, A dataDes) {
        Vertice<A> vOri = this.Graph.searchData(new Vertice<A>(dataOri));
        Vertice<A> vDes = this.Graph.searchData(new Vertice<A>(dataDes));

        if (vOri == null || vDes == null) {
            System.out.println("No se puede eliminar la arista. Vertice origen o destino no existen.");
            return;
        }

        Arista<A> e = new Arista<A>(vDes);
        if (!vOri.getConect().isEmpty() && vOri.getConect().search(e))
            vOri.getConect().remove(e);
        else
            System.out.println("No se puede eliminar la arista. La arista no existe.");

        Arista<A> eReverse = new Arista<A>(vOri);
        if (!vDes.getConect().isEmpty() && vDes.getConect().search(eReverse))
            vDes.getConect().remove(eReverse);
    }

    public void removeVertex(A data) {
        Vertice<A> vToRemove = this.Graph.searchData(new Vertice<A>(data));
        if (vToRemove == null) {
            System.out.println("No se puede eliminar el vertice. Vertice no existe.");
            return;
        }

        this.Graph.remove(vToRemove);

        // Remove all edges pointing to the removed vertex
        Node <Vertice<A>> currVertice = this.Graph.getRoot();
        while (currVertice != null) {
            Arista<A> eToRemove = new Arista<A>(vToRemove);
            currVertice.getData().getConect().remove(eToRemove);
            currVertice = currVertice.getNextNode();
        }
    }


    public boolean searchEdge(A vOri, A vDes) {
        Vertice<A> ori = this.Graph.searchData(new Vertice<A>(vOri));
        Vertice<A> des = this.Graph.searchData(new Vertice<A>(vDes));
        if (ori == null || des == null)
            return false;

        Arista<A> e = new Arista<A>(des);
        return ori.getConect().search(e);
    }

    public boolean searchVertex(A data) {
        Vertice<A> v = this.Graph.searchData(new Vertice<A>(data));
        return v != null;
    }
}