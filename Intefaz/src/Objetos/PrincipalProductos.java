import java.util.*;

public class PrincipalProductos {

    public static void main(String[] args) {
        EstructuraProductos trie = new EstructuraProductos();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de productos");

        // Agregar productos manualmente
        trie.insert(1, "manzana", 1.5, 5);
        trie.insert(2, "banana", 0.75, 7);
        trie.insert(3, "naranja", 2.0, 10);
        trie.insert(4, "pera", 1.0, 3);

        // Buscar productos
        System.out.print("Buscar producto: ");
        String nombreProducto = scanner.nextLine().toLowerCase();
        if (trie.search(nombreProducto)) {
            Producto productoEncontrado = trie.buscarProducto(nombreProducto);
            System.out.println("Producto encontrado: " + productoEncontrado.getDescripcion()
                    + " - Precio: " + productoEncontrado.getPrecio() + " - Stock: " + productoEncontrado.getStock());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
    
}