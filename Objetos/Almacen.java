package Objetos;

import Estructuras.LINKEDLIST.LinkedList;
import Estructuras.LINKEDLIST.Node;

public class Almacen{

    private Integer codigo;
    private String nombre;
    private String direccion;
    private LinkedList<Productos> productos; // Usamos LinkedList para almacenar los productos
    private static LinkedList<Almacen> almacenes = new LinkedList<>(); // Lista de todos los almacenes

    public Almacen (int code, String name, String adress) {
        this.codigo = code;
        this.nombre = name;
        this.direccion = adress;
        this.productos = new LinkedList<>();
        almacenes.insertLast(this);
    }

    public Almacen () {
        this(0, null, null);
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
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
    public void agregarProducto(Productos producto) {
        productos.insertLast(producto);
    }

    // Método para eliminar un producto del almacén
    public void eliminarProducto(Productos producto) {
        productos.remove(producto);
    }

    // Método para buscar un producto en todos los almacenes
    public static boolean buscarProductoEnAlmacenes(Productos producto) {
        Node<Almacen> aux = almacenes.getRoot();
        while (aux != null) {
            if (aux.getData().productos.search(producto)) {
                return true; // Si el producto se encuentra en algún almacén, retornamos true.
            }
            aux = aux.getNextNode();
        }
        return false; // Si no se encuentra el producto en ningún almacén, retornamos false.
    }

    // Método para buscar un almacén por su nombre
    public static Almacen buscarAlmacen(String nombre) {
        Node<Almacen> aux = almacenes.getRoot();
        while (aux != null) {
            if (aux.getData().getNombre().equals(nombre)) {
                return aux.getData();
            }
            aux = aux.getNextNode();
        }
        return null; // Si no se encuentra el almacén
    }

    // Método para obtener todos los productos en el almacén
    public LinkedList<Productos> getProductos() {
        return productos;
    }

    // Método para obtener la cantidad de productos en el almacén
    public int getCantidadProductos() {
        return productos.getSize();
    }

    // Método para establecer una ruta de distribución entre almacenes
    public void establecerRutaDistribucion(LinkedList<Almacen> almacenes) {
        // Implementar lógica de distribución aquí
    }

    @Override
    public String toString () {
        return "Nombre >>> "+this.nombre+"\nCodigo >>> "+this.codigo+"Direccion >>> "+this.direccion;
    }
}