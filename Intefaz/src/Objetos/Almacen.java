package Objetos;

import Estructuras.LINKEDLIST.LinkedList;
import Estructuras.LINKEDLIST.Node;

public class Almacen <A>{

    private String codigo;
    private String nombre;
    private String direccion;
    private LinkedList<A> productos; // Usamos LinkedList para almacenar los productos
    private static LinkedList<Almacen> todosAlmacenes = new LinkedList<>();


    public Almacen (String code, String name, String adress) {
        this.codigo = code;
        this.nombre = name;
        this.direccion = adress;
        this.productos = new LinkedList<A>();
        todosAlmacenes.insertLast(this);
    }

    public Almacen () {
        this(null, null, null);
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método para agregar un producto al almacén
    public void agregarProducto(A producto) {
        productos.insertLast(producto);
    }

    // Método para eliminar un producto del almacén
    public void eliminarProducto( A producto) {
        productos.remove(producto);
    }

    // Método para buscar un producto en todos los almacenes
    public boolean buscarProductoEnAlmacen( A data ) {
        if(this.productos.searchData(data)!=null)
            return true;
        return false; // Si no se encuentra el producto en ningún almacén, retornamos false.
    }

    public static Almacen buscarAlmacenPorCodigo(Integer codigo) {
        Node<Almacen> aux = todosAlmacenes.getRoot();
        while (aux != null) {
            if (aux.getData().getCodigo().equals(codigo)) {
                return aux.getData();
            }
            aux = aux.getNextNode();
        }
        return null; // Si no se encuentra el almacén
    }

    // Método para obtener todos los productos en el almacén
    public LinkedList<A> getProductos() {
        return productos;
    }

    public static void mostrarTodosLosAlmacenes() {
        System.out.println("Lista de Almacenes:");
        System.out.println("====================");
        for (Node<Almacen> aux = todosAlmacenes.getRoot(); aux != null; aux = aux.getNextNode()) {
            System.out.println(aux.getData().toString());
        }
    }

    public static boolean buscarProductoEnAlmacenes(Productos producto) {
        Node<Almacen> aux = todosAlmacenes.getRoot();
        while (aux != null) {
            if (aux.getData().buscarProductoEnAlmacen(producto)) {
                return true; // Si el producto se encuentra en algún almacén, retornamos true.
            }
            aux = aux.getNextNode();
        }
        return false; // Si no se encuentra el producto en ningún almacén, retornamos false.
    }

    private void trasladarProductos(Almacen almacenaDarDeBaja) {
        LinkedList<Productos> productosAlmacenBaja = almacenaDarDeBaja.getProductos();
        Node<Productos> aux = productosAlmacenBaja.getRoot();

        while (aux != null) {
            Productos producto = aux.getData();

            // Encontrar el almacén con menor cantidad de este producto
            Almacen destino = getAlmacenConMenorCantidadProducto();

            // Trasladar el producto al almacén destino
            destino.agregarProducto(producto);

            aux = aux.getNextNode();
        }
    }

    // Método para obtener el almacén con menor cantidad de un producto en la lista de almacenes
    private Almacen getAlmacenConMenorCantidadProducto() {
        Almacen destino = null;
        int minCantidad = Integer.MAX_VALUE;

        Node<Almacen> auxAlmacenes = todosAlmacenes.getRoot();
        while (auxAlmacenes != null) {
            int cantidadProducto = auxAlmacenes.getData().getCantidadProducto();
            if (cantidadProducto < minCantidad) {
                destino = auxAlmacenes.getData();
                minCantidad = cantidadProducto;
            }
            auxAlmacenes = auxAlmacenes.getNextNode();
        }

        return destino;
    }

    // Método para dar de baja un almacén
    public static void darDeBajaAlmacen(Almacen almacenaDarDeBaja) {
        // Trasladar los productos del almacén a otros almacenes
        almacenaDarDeBaja.trasladarProductos(almacenaDarDeBaja);

        // Eliminar el almacén de la lista de todos los almacenes
        todosAlmacenes.remove(almacenaDarDeBaja);
    }

    // Método para obtener la cantidad de un producto en el almacén
    private int getCantidadProducto() {
        int cantidad = 0;
        Node<A> aux = productos.getRoot();

        while (aux != null) {
            cantidad++;
            aux = aux.getNextNode();
        }

        return cantidad;
    }


    @Override
    public String toString () {
        return "Nombre >>> "+this.nombre+"\nCodigo >>> "+this.codigo+"Direccion >>> "+this.direccion;
    }
}