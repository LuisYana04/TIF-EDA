import Estructuras.AlmacenGrafo.Grafo;
import Estructuras.LINKEDLIST.LinkedList;
import Estructuras.LINKEDLIST.Node;

public class Test {
	public static void main(String[] args) {
		
		
		Grafo<String> g1 = new Grafo<String>();

		g1.insertVertice("Lima");
		g1.insertVertice("Arequipa");
		g1.insertVertice("Cuzco");
		g1.insertVertice("Tacna");
		g1.insertVertice("Moquegua");
		g1.insertVertice("Puno");
		System.out.println(g1+ "\n");
		
		
		g1.insertArista("Lima", "Cuzco");
		g1.insertArista("Arequipa", "Tacna");
		g1.insertArista("Tacna", "Lima");
		g1.insertArista("Cuzco", "Arequipa");
		g1.insertArista("Lima", "Arequipa");
		g1.insertArista("Lima", "Moquegua");
		g1.insertArista("Arequipa", "Puno");
		g1.insertArista("Puno", "Cuzco");
		g1.insertArista("Puno", "Cuzco");
		g1.insertArista("Puno000", "Cuzco");
		
		System.out.println(g1+ "\n");
		
        g1.removeVertex("Lima");
        System.out.println(g1+ "\n");

	}

}
