package proyecto;

import TreeBst.ArbolBinario;
import TreeBst.NodoArbol;

public class Almacen {
    private int codigo;
    private String nombre;
    private String direccion;
    private ArbolBinario<Producto> inventario;

    public Almacen(int codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.inventario = new ArbolBinario<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
    public ArbolBinario<Producto> getInventario() {
        return inventario;
    }

    
    public void agregarProducto(Producto producto) {
        Producto productoExistente = inventario.buscar(producto);
        if (productoExistente != null) {
            // Si el producto ya existe en el inventario, aumentamos su stock
            productoExistente.setStock(productoExistente.getStock() + producto.getStock());
        } else {
            // Si el producto no existe en el inventario, lo insertamos
            producto.setCodigoAlmacen(this.codigo);  // Asigna el código del almacén al producto
            inventario.insertar(producto);
        }
    }

    public Producto eliminarProducto(int codigoProducto) {
        Producto productoAEliminar = new Producto(codigoProducto, "", 0, this.codigo);  // Código del almacén agregado al constructor
        return inventario.eliminar(productoAEliminar);
    }

    public Producto buscarProducto(int codigoProducto) {
        Producto productoABuscar = new Producto(codigoProducto, "", 0, this.codigo);  // Código del almacén agregado al constructor
        return inventario.buscar(productoABuscar);
    }

    public void mostrarProductos() {
        System.out.println("Productos en el almacén " + nombre + ":");
        inventario.recorridoInorden();
    }

    public void extraerProducto(int codigoProducto, int cantidad) {
        Producto producto = buscarProducto(codigoProducto);
        if (producto != null) {
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                System.out.println("Se extrajeron " + cantidad + " unidades del producto " + producto.getDescripcion());
            } else {
                System.out.println("No hay suficiente stock del producto " + producto.getDescripcion() + ".");
            }
        } else {
            System.out.println("El producto con código " + codigoProducto + " no existe en este almacén.");
        }
    }

    public int totalProductos() {
        return inventario.getStockTotal();
    }   

    @Override
    public String toString() {
        return nombre;
    }
}