package graphlink;

import java.util.ArrayList;
import java.util.List;

import PriorityQueue.PriorityQueue;

import listlinked.ListLinked;
import listlinked.Node;
import proyecto.Almacen;
import proyecto.ResultDistr;
public class GraphLink <E>{
	public ListLinked<Vertex<E>> listVertex; //ListLinked es la lista enlazada que han implementado en sus labs.
	
	public GraphLink() {
		this.listVertex = new ListLinked<Vertex<E>>();
	}
	
	public void insertVertex(E data) {
		Vertex<E> v = new Vertex<E>(data);
		if (this.listVertex.search(v))
			System.out.println("Vertice con "+ data +" ya fue insertado");
		else
			this.listVertex.insertFirst(v);
	}
	
	public void insertEdge(E dataOri, E dataDes, int weight) {  
		Vertex<E> vOri = this.listVertex.searchData(new Vertex<E>(dataOri));
		Vertex<E> vDes = this.listVertex.searchData(new Vertex<E>(dataDes));
		
		if (vOri == null || vDes == null) {
			System.out.println(dataOri + " o " + dataDes + " no existen....");
		} else {
			Edge<E> e = new Edge<E>(vDes, weight);
			if (vOri.listAdj.search(e)) {
				System.out.println("Arista ("+ dataOri + "," + dataDes + ") ya fue insertada...");
			} else {
				vOri.listAdj.insertFirst(e);
				// No necesitas insertar una arista en vDes, ya que vOri es la referencia de destino en la arista creada
			}
		}
	}
	
	@Override
	public String toString() {
		return this.listVertex.toString();
	}
	
	
	public void removeEdge(E dataOri, E dataDes) {
		Vertex<E> vOri = this.listVertex.searchData(new Vertex<E>(dataOri));
		Vertex<E> vDes = this.listVertex.searchData(new Vertex<E>(dataDes));
		
		if (vOri == null || vDes == null) {
			System.out.println(dataOri + " o " + dataDes + " no existen....");
		} else {
			vOri.listAdj.remove(new Edge<E>(vDes));
			vDes.listAdj.remove(new Edge<E>(vOri));
		}
	}

	public void removeVertex(E data) {
		Vertex<E> v = this.listVertex.searchData(new Vertex<E>(data));
		if (v == null) {
			System.out.println(data + " no existe....");
		} else {
			for (Node<Vertex<E>> aux = this.listVertex.getHead(); aux != null; aux = aux.getNext()) {
				aux.getData().listAdj.remove(new Edge<E>(v));
			}
			this.listVertex.remove(v);
		}
	}

	public boolean searchEdge(E dataOri, E dataDes) {
		Vertex<E> vOri = this.listVertex.searchData(new Vertex<E>(dataOri));
		Vertex<E> vDes = this.listVertex.searchData(new Vertex<E>(dataDes));
		
		if (vOri == null || vDes == null) {
			return false;
		} else {
			return vOri.listAdj.search(new Edge<E>(vDes));
		}
	}

	public boolean searchVertex(E data) {
		return this.listVertex.search(new Vertex<E>(data));
	}

	public ResultDistr dijkstra(E initialVertex, E finalVertex) {
		// Busca los vértices de inicio y fin en la lista de vértices
		Vertex<E> s = this.listVertex.searchData(new Vertex<E>(initialVertex));
		Vertex<E> f = this.listVertex.searchData(new Vertex<E>(finalVertex));
	
		// Establece la distancia del vértice inicial a 0
		s.setDist(0);
	
		// Crea una cola de prioridad para los vértices que aún no se han procesado
		PriorityQueue<Vertex<E>> queue = new PriorityQueue<>();
		queue.offer(s);
	
		// Itera hasta que la cola de prioridad esté vacía
		while (!queue.isEmpty()) {
			// Toma el vértice con la distancia más corta de la cola de prioridad
			Vertex<E> u = queue.poll();
	
			// Si el vértice actual es el vértice final, detén el bucle
			if (u.equals(f)) {
				break;
			}
	
			// Marca u como visitado
			u.setVisited(true);
	
			// Para cada vértice adyacente v de u
			for (Node<Edge<E>> aux = u.listAdj.getHead(); aux != null; aux = aux.getNext()) {
				Vertex<E> v = aux.getData().refdest;
	
				// Si la distancia a v puede acortarse
				if (!v.isVisited() && u.getDist() != Integer.MAX_VALUE && u.getDist() + aux.getData().weight < v.getDist()) {
					// Actualiza la distancia de v
					v.setDist(u.getDist() + aux.getData().weight);
	
					// Actualiza el vértice anterior de v
					v.setPrev(u);
	
					// Ofrece el vértice v en la cola de prioridad
					queue.offer(v);
				}
			}
		}
	
		// Crea una lista para almacenar el camino más corto
		List<Almacen> shortestPath = new ArrayList<>();
	
		// Inicializa la distancia total a 0
		int totalDistance = 0;
	
		// Inicializa el vértice actual como el vértice final
		Vertex<E> current = f;
	
		// Mientras el vértice actual no sea nulo
		while (current != null) {
			// Agrega el vértice actual al principio del camino más corto
			shortestPath.add(0, (Almacen) current.getData());
	
			// Suma la distancia del vértice actual a la distancia total
			totalDistance += current.getDist();
	
			// Establece el vértice actual como su vértice anterior
			current = current.getPrev();
		}
	
		// Devuelve el ResultDistr que contiene el camino más corto y la distancia total
		return new ResultDistr(shortestPath, totalDistance);
	}
	
}
