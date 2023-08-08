package proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia del grafo para gestionar los almacenes y las vías
        GrafoAlmacenes grafoAlmacenes = new GrafoAlmacenes();

        // Crear un Scanner para leer la entrada del usuario
        Scanner input = new Scanner(System.in);

        // Cargar información de almacenes desde el archivo "almacenes.csv"
        try {
            File almacenesFile = new File("archivos/almacenes.csv");
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

        // Cargar información de productos desde el archivo "productos.csv"
        try {
            File productosFile = new File("archivos/productos.csv");
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
                int codigoAlmacen = Integer.parseInt(campos[3]);  // Nuevo campo

                // Buscar el almacén donde se agregará el producto
                Almacen almacen = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacen);

                if (almacen != null) {
                    // Crear un objeto Producto con la información cargada
                    Producto producto = new Producto(codigoProducto, descripcion, stock, codigoAlmacen);  // Código del almacén agregado al constructor

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

        // Cargar información de vías desde el archivo "vias.csv"
        try {
            File viasFile = new File("archivos/rutas.csv");
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

        System.out.println("¿Deseas agregar un nuevo producto? (s/n)");
        String respuesta = input.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingresa el código del producto:");
            int codigoProducto = input.nextInt();
            input.nextLine();  // Consumir la línea restante

            System.out.println("Ingresa la descripción del producto:");
            String descripcion = input.nextLine();

            System.out.println("Ingresa el stock del producto:");
            int stock = input.nextInt();
            input.nextLine();  // Consumir la línea restante

            System.out.println("Ingresa el código del almacén donde se agregará el producto:");
            int codigoAlmacen = input.nextInt();
            input.nextLine();  // Consumir la línea restante

            // Crear el producto y agregarlo al almacén correspondiente
            Producto producto = new Producto(codigoProducto, descripcion, stock, codigoAlmacen);  // Código del almacén agregado al constructor
            grafoAlmacenes.agregarProducto(codigoAlmacen, producto);

            // Agregar el producto al archivo CSV
            try {
                FileWriter writer = new FileWriter("archivos/productos.csv", true);
                writer.append(codigoProducto + ";" + descripcion + ";" + stock + ";" + codigoAlmacen + "\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("¿Deseas extraer un producto? (s/n)");
        String respuesta2 = input.nextLine();

        if (respuesta2.equalsIgnoreCase("s")) {
            System.out.println("Ingresa el código del producto:");
            int codigoProducto = input.nextInt();
            input.nextLine();  // Consumir la línea restante

            System.out.println("Ingresa la cantidad a extraer:");
            int cantidad = input.nextInt();
            input.nextLine();  // Consumir la línea restante

            System.out.println("Ingresa el código del almacén del que se extraerá el producto:");
            int codigoAlmacen = input.nextInt();
            input.nextLine();  // Consumir la línea restante

            // Extraer el producto del almacén correspondiente
            Almacen almacen = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacen);
            if (almacen != null) {
                almacen.extraerProducto(codigoProducto, cantidad);
            } else {
                System.out.println("El almacén con código " + codigoAlmacen + " no existe.");
            }
        }

        // Pedir al usuario los códigos de los almacenes de inicio y fin
        System.out.println("Ingrese el código del almacén de inicio:");
        int codigoAlmacenInicio = input.nextInt();
        input.nextLine();  // Consumir la línea restante

        System.out.println("Ingrese el código del almacén de fin:");
        int codigoAlmacenFin = input.nextInt();
        input.nextLine();  // Consumir la línea restante

        // Buscar los almacenes de inicio y fin en el grafo
        Almacen almacenInicio = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacenInicio);
        Almacen almacenFin = grafoAlmacenes.buscarAlmacenPorCodigo(codigoAlmacenFin);

        if (almacenInicio != null && almacenFin != null) {
            // Usar Dijkstra para encontrar la ruta más corta desde el almacén de inicio al almacén de fin
            ResultDistr resultDistr = grafoAlmacenes.dijkstra(almacenInicio, almacenFin);

            // Obtener la ruta más corta y la distancia total de resultDistr
            List<Almacen> rutaMasCorta = resultDistr.getResult();
            int distanciaTotal = resultDistr.getTotalDistance();

            // Imprimir la ruta más corta
            System.out.println("La ruta más corta desde el almacén " + codigoAlmacenInicio + " hasta el almacén " + codigoAlmacenFin + " es:");
            for (Almacen almacen : rutaMasCorta) {
                System.out.println(almacen.getNombre());
            }

            // Imprimir la distancia total
            System.out.println("La distancia total es: " + distanciaTotal);
        } else {
            System.out.println("Uno o ambos códigos de almacén no existen.");
        }

        System.out.println("¿Deseas eliminar un almacén? (s/n)");
        String respuesta3 = input.nextLine().trim();

        if (respuesta3.equalsIgnoreCase("s")) {
            System.out.println("Ingresa el código del almacén a eliminar:");
            int codigoAlmacen = input.nextInt();
            input.nextLine();  // Consumir la línea restante

            // Eliminar el almacén correspondiente
            grafoAlmacenes.eliminarAlmacen(codigoAlmacen);
        }

        // Cerrar el Scanner
        input.close();
    }
}
