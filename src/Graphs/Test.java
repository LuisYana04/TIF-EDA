
import graphlink.GraphLink;

public class Test {

	public static void main(String[] args) {
		
		
		GraphLink<String> g1 = new GraphLink<String>();
		GraphLink<String> graph = new GraphLink<>();

        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        // Insertar aristas con pesos
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "C", 5);
        graph.insertEdge("B", "D", 10);
        graph.insertEdge("C", "D", 3);
        graph.insertEdge("D", "E", 7);

        // Ejecutar el algoritmo de Dijkstra desde el vértice "A"
        //graph.dijkstra("A");
    
		
	}

}
