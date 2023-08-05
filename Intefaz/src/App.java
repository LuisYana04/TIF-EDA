import Objetos.Almacen;
import java.io.IOException;
import Estructuras.AlmacenGrafo.Grafo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        /*
         * CODIGO DE EJECUCION:
         * /usr/bin/env /usr/lib/jvm/jdk-17.0.8/bin/java --module-path ~/Documentos/LIB/javafx-sdk-20.0.2/lib/ --add-modules javafx.controls,javafx.fxml @/tmp/cp_cebk4ll6d9wvdvomjyh5kio56.argfile App cd /home/francis/Escritorio/EDA_TEO/Proyecto_EDA_Teo-Grupo-3 ; /usr/bin/env /usr/lib/jvm/jdk-17.0.8/bin/java @/tmp/cp_cebk4ll6d9wvdvomjyh5kio56.argfile App 
         */
        Grafo <Almacen> Almacenes = new Grafo <Almacen> ();
        launch(args);
    }

    @Override
    public void start(Stage inicio) throws Exception {  
        /*
        * System.out.println(getClass().getResource("Interfaz.fxml"));
        * Ruta Relativa del doc.fxml
         */
        FXMLLoader doc = new FXMLLoader(getClass().getResource("Interfaz.fxml"));
        Parent root = doc.load ();
        Scene grind = new Scene (root);
        inicio.setTitle("ALMACEN");
        inicio.setScene(grind);
        inicio.show();
    }
}
