package proyecto;

import javax.swing.*;

import graphlink.Vertex;
import listlinked.ListLinked;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GUI {
    private static GrafoAlmacenes grafoAlmacenes;
    private static JFrame frame;
    private static JTextArea outputTextArea;

    public static void main(String[] args) {
        // Crear una instancia del grafo para gestionar los almacenes y las vías
        grafoAlmacenes = new GrafoAlmacenes();

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void loadAlmacenesFromFile(String fileName) {
        try {
            File almacenesFile = new File(fileName);
            Scanner scanner = new Scanner(almacenesFile);

            // Ignorar la primera línea del archivo que contiene los encabezados
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(";");

                int codigo = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String direccion = campos[2];

                // Crear un objeto Almacen con la información cargada
                Almacen almacen = new Almacen(codigo, nombre, direccion);

                // Agregar el almacén al grafo
                grafoAlmacenes.insertVertex(almacen);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadProductosFromFile(String fileName) {
        try {
            File productosFile = new File(fileName);
            Scanner scanner = new Scanner(productosFile);

            // Ignorar la primera línea del archivo que contiene los encabezados
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(";");

                int codigoProducto = Integer.parseInt(campos[0]);
                String descripcion = campos[1];
                int stock = Integer.parseInt(campos[2]);
                int codigoAlmacen = Integer.parseInt(campos[3]);

                // Buscar el almacén donde se agregará el producto
                Almacen almacen = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacen);

                if (almacen != null) {
                    // Crear un objeto Producto con la información cargada
                    Producto producto = new Producto(codigoProducto, descripcion, stock, codigoAlmacen);

                    // Agregar el producto al almacén correspondiente en el grafo
                    almacen.agregarProducto(producto);
                } else {
                    System.out.println("El almacén con código " + codigoAlmacen + " no existe.");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadViasFromFile(String fileName) {
        try {
            File viasFile = new File(fileName);
            Scanner scanner = new Scanner(viasFile);

            // Ignorar la primera línea del archivo que contiene los encabezados
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(";");

                int codigoAlmacenOrigen = Integer.parseInt(campos[0]);
                int distancia = Integer.parseInt(campos[1]);
                int codigoAlmacenDestino = Integer.parseInt(campos[2]);

                // Buscar los almacenes de origen y destino en el grafo
                Almacen almacenOrigen = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacenOrigen);
                Almacen almacenDestino = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacenDestino);

                if (almacenOrigen != null && almacenDestino != null) {
                    // Agregar la vía al grafo
                    grafoAlmacenes.insertEdge(almacenOrigen, almacenDestino, distancia);
                } else {
                    System.out.println("Uno de los almacenes en la vía con código " + codigoAlmacenOrigen + "-" + codigoAlmacenDestino + " no existe.");
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createAndShowGUI() {
        // Create and set up the main frame
        frame = new JFrame("Warehouse Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    
        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns, 10 pixels horizontal and vertical gap
    
        // Create buttons
        JButton addProductButton = new JButton("Agregar Producto");
        JButton extractProductButton = new JButton("Extraer Product");
        JButton findShortestRouteButton = new JButton("Encontrar Ruta Rapida");
        JButton deleteWarehouseButton = new JButton("Borrar Almacen");
        JButton viewProductsButton = new JButton("Ver Productos");
        JButton addWarehouseButton = new JButton("Agregar Almacen");
        JButton uploadAlmacenesButton = new JButton("Subir Almacenes");
        JButton uploadProductosButton = new JButton("Subir Productos");
        JButton uploadRutasButton = new JButton("Subir Rutas");
        JButton viewAlmacenesButton = new JButton("Ver Almacenes");
        JButton searchProductButton = new JButton("Buscar Producto en Todos los Almacenes");
        JButton actualizarArchivosButton = new JButton("Actualizar Archivos");

        // Add action listeners to the buttons

        actualizarArchivosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarArchivos();
            }
        });

        searchProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });

        viewAlmacenesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAlmacenes();
            }
        });

        uploadAlmacenesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadAlmacenesFromFile();
            }
        });
    
        uploadProductosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadProductosFromFile();
            }
        });
    
        uploadRutasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadRutasFromFile();
            }
        });

        addWarehouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWarehouse();
            }
        });
    
        viewProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });
    
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
    
        extractProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                extractProduct();
            }
        });
    
        findShortestRouteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findShortestRoute();
            }
        });
    
        deleteWarehouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteWarehouse();
            }
        });
    
        // Add buttons to the button panel
        buttonPanel.add(addProductButton);
        buttonPanel.add(extractProductButton);
        buttonPanel.add(findShortestRouteButton);
        buttonPanel.add(deleteWarehouseButton);
        buttonPanel.add(addWarehouseButton);
        buttonPanel.add(viewProductsButton);
        buttonPanel.add(searchProductButton);  
        buttonPanel.add(viewAlmacenesButton);
        buttonPanel.add(uploadAlmacenesButton);
        buttonPanel.add(uploadProductosButton);
        buttonPanel.add(uploadRutasButton);
        buttonPanel.add(actualizarArchivosButton);
    
        // Create a text area to display output
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
    
        // Create a scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
    
        // Add the button panel and scroll pane to the main frame
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
    
        // Set the size and make the frame visible
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private static void actualizarArchivos() {
        // Llamar a los métodos de actualización de CSV del grafo
        grafoAlmacenes.actualizarAlmacenesCSV();
        grafoAlmacenes.actualizarProductosCSV();
        grafoAlmacenes.actualizarRutasCSV();

        // Mostrar un mensaje de confirmación al usuario
        JOptionPane.showMessageDialog(frame, "Archivos actualizados correctamente.");
    }

    private static void searchProduct() {
        // Método para buscar un producto en todos los almacenes

        // Solicitar al usuario el código del producto a buscar
        String codigoProductoStr = JOptionPane.showInputDialog(frame, "Ingresa el código del producto a buscar:");
        if (codigoProductoStr == null) return; // El usuario canceló la operación

        try {
            int codigoProducto = Integer.parseInt(codigoProductoStr);

            // Buscar el producto en todos los almacenes del grafo
            Producto productoEnAlmacen = grafoAlmacenes.buscarProductoEnTodosAlmacenes(codigoProducto);

            if (productoEnAlmacen != null) {
                // El producto fue encontrado en algún almacén
                String mensaje = "El producto con código " + codigoProducto + " fue encontrado en el almacén:\n";
                mensaje += "Nombre del almacén: " + grafoAlmacenes.buscarAlmacenPorCodigo(productoEnAlmacen.getCodigoAlmacen()).getNombre() + "\n";
                mensaje += "Descripción del producto: " + productoEnAlmacen.getDescripcion() + "\n";
                mensaje += "Stock: " + productoEnAlmacen.getStock() + "\n";
                JOptionPane.showMessageDialog(frame, mensaje);
            } else {
                // El producto no fue encontrado en ningún almacén
                JOptionPane.showMessageDialog(frame, "El producto con código " + codigoProducto + " no fue encontrado en ningún almacén.");
            }
        } catch (NumberFormatException ex) {
            // Error en la conversión del código del producto
            JOptionPane.showMessageDialog(frame, "Ingresa un código de producto válido (número entero).");
        }
    }

    private static void viewAlmacenes() {
    // Get all almacenes from the grafo
    ListLinked<Vertex<Almacen>> vertices = grafoAlmacenes.listVertex;

    // Show the almacenes in the text area
    StringBuilder almacenesStr = new StringBuilder("All Almacenes:\n");
    for (Vertex<Almacen> vertex : vertices) {
        Almacen almacen = vertex.getData();
        almacenesStr.append(almacen.toString()).append("\n");
    }
    outputTextArea.setText(almacenesStr.toString());
}

    private static void uploadAlmacenesFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadAlmacenesFromFile(selectedFile.getAbsolutePath());
            JOptionPane.showMessageDialog(frame, "Almacenes cargados correctamente desde el archivo.");
        }
    }
    
    private static void uploadProductosFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadProductosFromFile(selectedFile.getAbsolutePath());
            JOptionPane.showMessageDialog(frame, "Productos cargados correctamente desde el archivo.");
        }
    }
    
    private static void uploadRutasFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadViasFromFile(selectedFile.getAbsolutePath());
            JOptionPane.showMessageDialog(frame, "Rutas cargadas correctamente desde el archivo.");
        }
    }

    private static void addWarehouse() {
        String codigoStr = JOptionPane.showInputDialog(frame, "Ingresa el código del almacén:");
        if (codigoStr == null) return;
        int codigo = Integer.parseInt(codigoStr);
    
        String nombre = JOptionPane.showInputDialog(frame, "Ingresa el nombre del almacén:");
        if (nombre == null) return;
    
        String direccion = JOptionPane.showInputDialog(frame, "Ingresa la dirección del almacén:");
        if (direccion == null) return;
    
        // Agregar el almacén utilizando el método agregarAlmacen de GrafoAlmacenes
        grafoAlmacenes.agregarAlmacen(codigo, nombre, direccion);
    
        // Show confirmation message to the user
        JOptionPane.showMessageDialog(frame, "Almacén agregado correctamente.");
    }
    

    private static void viewProducts() {
        String codigoAlmacenStr = JOptionPane.showInputDialog(frame, "Ingresa el código del almacén para ver sus productos:");
        if (codigoAlmacenStr == null) return;
        int codigoAlmacen = Integer.parseInt(codigoAlmacenStr);
    
        // Buscar el almacén en el grafo
        Almacen almacen = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacen);
        if (almacen != null) {
            // Get the products of the warehouse
            ArrayList<Producto> productos = almacen.getInventario().getProductos();
    
            // Show the products in the text area
            StringBuilder productsStr = new StringBuilder("Productos en el almacén " + almacen.getNombre() + ":\n");
            for (Producto producto : productos) {
                productsStr.append(producto.toString()).append("\n");
            }
            outputTextArea.setText(productsStr.toString());
        } else {
            outputTextArea.setText("El almacén con código " + codigoAlmacen + " no existe.");
        }
    }

    private static void addProduct() {
        // Code to add a new product based on user input
        String codigoProductoStr = JOptionPane.showInputDialog(frame, "Ingresa el código del producto:");
        if (codigoProductoStr == null) return; // User canceled the operation
        int codigoProducto = Integer.parseInt(codigoProductoStr);

        String descripcion = JOptionPane.showInputDialog(frame, "Ingresa la descripción del producto:");
        if (descripcion == null) return;

        String stockStr = JOptionPane.showInputDialog(frame, "Ingresa el stock del producto:");
        if (stockStr == null) return;
        int stock = Integer.parseInt(stockStr);

        String codigoAlmacenStr = JOptionPane.showInputDialog(frame, "Ingresa el código del almacén donde se agregará el producto:");
        if (codigoAlmacenStr == null) return;
        int codigoAlmacen = Integer.parseInt(codigoAlmacenStr);

        // Crear el producto y agregarlo al almacén correspondiente
        Producto producto = new Producto(codigoProducto, descripcion, stock, codigoAlmacen);
        grafoAlmacenes.agregarProducto(codigoAlmacen, producto);

        // Agregar el producto al archivo CSV
        try {
            FileWriter writer = new FileWriter("archivos/productos.csv", true);
            writer.append(codigoProducto + ";" + descripcion + ";" + stock + ";" + codigoAlmacen + "\r\n");
            writer.close();

            // Show confirmation message to the user
            JOptionPane.showMessageDialog(frame, "Producto agregado correctamente.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void extractProduct() {
        // Code to extract a product based on user input
        String codigoProductoStr = JOptionPane.showInputDialog(frame, "Ingresa el código del producto:");
        if (codigoProductoStr == null) return; // User canceled the operation
        int codigoProducto = Integer.parseInt(codigoProductoStr);

        String cantidadStr = JOptionPane.showInputDialog(frame, "Ingresa la cantidad a extraer:");
        if (cantidadStr == null) return;
        int cantidad = Integer.parseInt(cantidadStr);

        String codigoAlmacenStr = JOptionPane.showInputDialog(frame, "Ingresa el código del almacén del que se extraerá el producto:");
        if (codigoAlmacenStr == null) return;
        int codigoAlmacen = Integer.parseInt(codigoAlmacenStr);

        // Extraer el producto del almacén correspondiente
        Almacen almacen = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacen);
        if (almacen != null) {
            almacen.extraerProducto(codigoProducto, cantidad);
            JOptionPane.showMessageDialog(frame, "Producto extraído correctamente.");
        } else {
            JOptionPane.showMessageDialog(frame, "El almacén con código " + codigoAlmacen + " no existe.");
        }
    }

    private static void findShortestRoute() {
        // Code to find the shortest route based on user input
        String codigoAlmacenInicioStr = JOptionPane.showInputDialog(frame, "Ingrese el código del almacén de inicio:");
        if (codigoAlmacenInicioStr == null) return;
        int codigoAlmacenInicio = Integer.parseInt(codigoAlmacenInicioStr);

        String codigoAlmacenFinStr = JOptionPane.showInputDialog(frame, "Ingrese el código del almacén de fin:");
        if (codigoAlmacenFinStr == null) return;
        int codigoAlmacenFin = Integer.parseInt(codigoAlmacenFinStr);

        // Buscar los almacenes de inicio y fin en el grafo
        Almacen almacenInicio = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacenInicio);
        Almacen almacenFin = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacenFin);

        if (almacenInicio != null && almacenFin != null) {
            // Usar Dijkstra para encontrar la ruta más corta desde el almacén de inicio al almacén de fin
            ResultDistr resultDistr = grafoAlmacenes.dijkstra(almacenInicio, almacenFin);

            // Obtener la ruta más corta y la distancia total de resultDistr
            List<Almacen> rutaMasCorta = resultDistr.getResult();
            int distanciaTotal = resultDistr.getTotalDistance();

            // Mostrar la ruta más corta y la distancia total
            StringBuilder rutaStr = new StringBuilder("La ruta más corta desde el almacén " + codigoAlmacenInicio + " hasta el almacén " + codigoAlmacenFin + " es:\n");
            for (Almacen almacen : rutaMasCorta) {
                rutaStr.append(almacen.getNombre()).append("\n");
            }
            rutaStr.append("La distancia total es: ").append(distanciaTotal);
            outputTextArea.setText(rutaStr.toString());
        } else {
            JOptionPane.showMessageDialog(frame, "Uno o ambos códigos de almacén no existen.");
        }
    }

    private static void deleteWarehouse() {
        // Code to delete a warehouse based on user input
        String codigoAlmacenStr = JOptionPane.showInputDialog(frame, "Ingresa el código del almacén a eliminar:");
        if (codigoAlmacenStr == null) return;
        int codigoAlmacen = Integer.parseInt(codigoAlmacenStr);

        // Eliminar el almacén correspondiente
        grafoAlmacenes.eliminarAlmacen(codigoAlmacen);
        JOptionPane.showMessageDialog(frame, "Almacén eliminado correctamente.");
    }
}