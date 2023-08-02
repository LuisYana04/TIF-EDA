import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        launch(args);
    }

    @Override
    public void start(Stage inicio) throws Exception {
        inicio.setTitle("Almacenes");
        Button aceptar = new Button ("Aceptar");
        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("Se hixo click");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().addAll(aceptar);
        inicio.setScene(new Scene(root, 300, 250));
        inicio.show();
    }
}
