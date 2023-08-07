package proyecto;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import graphlink.Edge;
import graphlink.GraphLink;
import graphlink.Vertex;

public class GrafoAlmacenes extends GraphLink<Almacen> {
    private static final String PATH_ALMACENES = "archivos/almacenes.csv";
    private static final String PATH_PRODUCTOS = "archivos/productos.csv";
    private static final String PATH_RUTAS = "archivos/rutas.csv";
    public GrafoAlmacenes() {
        super();
    }
    public Almacen buscarAlmacenPorCodigo(int codigo) {
        // Implementa aquí la lógica para buscar un almacén por su código
        // Recorre los vértices en this.listVertex y busca el almacén con el código dado
        // Devuelve el almacén si lo encuentra, o null si no existe
        for (Vertex<Almacen> vertex : this.listVertex) {
            Almacen almacen = vertex.getData();
            if (almacen.getCodigo() == codigo) {
                return almacen;
            }
        }
        return null;
    }

    public void agregarProducto(int codigoAlmacen, Producto producto) {
        // Implementa aquí la lógica para agregar un producto a un almacén específico
        // Puedes usar el método buscarAlmacenPorCodigo para obtener el almacén
        // Luego, agrega el producto al almacén si este existe, o muestra un mensaje de error si no existe
        Almacen almacen = buscarAlmacenPorCodigo(codigoAlmacen);
        if (almacen != null) {
            almacen.agregarProducto(producto);

            // Llamar al método de actualización del archivo CSV después de agregar el producto
            actualizarProductosCSV();
        } else {
            System.out.println("El almacén con código " + codigoAlmacen + " no existe.");
        }
    }

    public void eliminarProducto(int codigoAlmacen, int codigoProducto) {
        // Implementa aquí la lógica para eliminar un producto de un almacén específico
        // Puedes usar el método buscarAlmacenPorCodigo para obtener el almacén
        // Luego, elimina el producto del almacén si este existe, o muestra un mensaje de error si no existe
        Almacen almacen = buscarAlmacenPorCodigo(codigoAlmacen);
        if (almacen != null) {
            almacen.eliminarProducto(codigoProducto);

            // Llamar al método de actualización del archivo CSV después de eliminar el producto
            actualizarProductosCSV();
        } else {
            System.out.println("El almacén con código " + codigoAlmacen + " no existe.");
        }
    }

    public void eliminarAlmacen(int codigoAlmacen) {
        Almacen almacenAEliminar = buscarAlmacenPorCodigo(codigoAlmacen);
        if (almacenAEliminar != null) {
            // Buscar el almacén con menos productos
            Almacen almacenConMenosProductos = getAlmacenConMenosProductos();

            // Trasladar todos los productos del almacén eliminado al almacén con menos productos
            for (Producto producto : almacenAEliminar.getInventario().getProductos()) {
                almacenConMenosProductos.agregarProducto(producto);
            }

            // Eliminar el almacén
            super.removeVertex(almacenAEliminar);

            // Llamar al método de actualización del archivo CSV después de eliminar el almacén
            actualizarAlmacenesCSV();
            actualizarProductosCSV();
            actualizarRutasCSV();
        } else {
            System.out.println("El almacén con código " + codigoAlmacen + " no existe.");
        }
    }

    public void agregarAlmacen(int codigo, String nombre, String direccion) {
        // Verificar si ya existe un almacén con el mismo código
        if (buscarAlmacenPorCodigo(codigo) != null) {
            System.out.println("Ya existe un almacén con el código " + codigo);
            return;
        }
    
        // Crear un nuevo objeto Almacen con los datos proporcionados
        Almacen nuevoAlmacen = new Almacen(codigo, nombre, direccion);
    
        // Crear un nuevo objeto Vertex<Almacen> con el almacén creado
        Vertex<Almacen> nuevoVertex = new Vertex<Almacen>(nuevoAlmacen);
    
        // Agregar el nuevo vértice al grafo
        listVertex.insertLast(nuevoVertex);
    
        // Llamar al método de actualización del archivo CSV después de agregar el almacén
        actualizarAlmacenesCSV();
    }

    public Producto buscarProducto(int codigoAlmacen, int codigoProducto) {
        // Implementa aquí la lógica para buscar un producto en un almacén específico
        // Puedes usar el método buscarAlmacenPorCodigo para obtener el almacén
        // Luego, busca el producto en el almacén si este existe, o devuelve null si no existe
        Almacen almacen = buscarAlmacenPorCodigo(codigoAlmacen);
        if (almacen != null) {
            return almacen.buscarProducto(codigoProducto);
        } else {
            System.out.println("El almacén con código " + codigoAlmacen + " no existe.");
            return null;
        }
    }

    public Producto buscarProductoEnTodosAlmacenes(int codigoProducto) {
        for (Vertex<Almacen> vertex : listVertex) {
            Almacen almacen = vertex.getData();
            Producto productoEnAlmacen = almacen.buscarProducto(codigoProducto);
            if (productoEnAlmacen != null) {
                // El producto fue encontrado en este almacén
                return productoEnAlmacen;
            }
        }
        // El producto no fue encontrado en ningún almacén
        return null;
    }
    
    public Almacen getAlmacenConMenosProductos() {
        Almacen almacenConMenosProductos = null;
        int minStock = Integer.MAX_VALUE;
    
        for (Vertex<Almacen> vertex : this.listVertex) {
            Almacen almacen = vertex.getData();
            int stockTotal = almacen.getInventario().getStockTotal();
            if (stockTotal < minStock) {
                minStock = stockTotal;
                almacenConMenosProductos = almacen;
            }
        }
    
        return almacenConMenosProductos;
    }
    
    public void actualizarAlmacenesCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH_ALMACENES))) {
            writer.println("Codigo;Nombre;Direccion");
            for (Vertex<Almacen> vertex : this.listVertex) {
                Almacen almacen = vertex.getData();
                writer.println(almacen.getCodigo() + ";" + almacen.getNombre() + ";" + almacen.getDireccion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProductosCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH_PRODUCTOS))) {
            writer.println("Codigo;Descripcion;Stock;CodigoAlmacen");
            for (Vertex<Almacen> vertex : this.listVertex) {
                Almacen almacen = vertex.getData();
                for (Producto producto : almacen.getInventario().getProductos()) {
                    writer.println(producto.getCodigo() + ";" + producto.getDescripcion() + ";" +
                            producto.getStock() + ";" + producto.getCodigoAlmacen());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarRutasCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH_RUTAS))) {
            writer.println("codigoAlmacenOrigen;distancia;codigoAlmacenDestino");
            for (Vertex<Almacen> vertex : this.listVertex) {
                Almacen almacen = vertex.getData();
                for (Edge<Almacen> edge : vertex.getEdges()) {
                    writer.println(almacen.getCodigo() + ";" + edge.getWeight() + ";" + edge.getDest().getData().getCodigo());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Otros métodos que puedas necesitar para trabajar con almacenes y productos
}