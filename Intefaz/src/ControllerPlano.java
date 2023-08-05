import java.util.LinkedList;
import java.util.Random;
import Estructuras.LINKEDLIST.*;
import Estructuras.AlmacenGrafo.*;
import Objetos.Almacen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class ControllerPlano {
    private LinkedList <Vertice <Almacen>> Almacenes;

    @FXML
    private AnchorPane Plano;

    @FXML
    void Agregar(ActionEvent event) {
    }

    @FXML
    void Calcular(ActionEvent event) {

    }

    @FXML
    void Separar(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {
        System.out.println(RecorrerR(this.Almacenes.getRoot()));
    }

    void CrearNodo(Almacen aux){
        Random random = new Random();
        double Ordenadas = random.nextDouble()* (250 * 2 + 1) - 250;        
        double Absisas = random.nextDouble()* (250 * 2 + 1) - 250;
        Label name = new Label(aux.getNombre());
        Circle nodoG = new Circle();
        nodoG.setRadius(10);
        // Ajusta el espacio entre el nombre y el círculo según tus necesidades
        HBox nodo = new HBox(10, name, nodoG);
        Plano.getChildren().add(nodo);

        // Centrar el HBox en el AnchorPane
        double centerX = (Plano.getWidth() / 2)+Ordenadas;
        double centerY = (Plano.getHeight() / 2)+Absisas;
        double nodoWidth = nodo.getBoundsInParent().getWidth();
        double nodoHeight = nodo.getBoundsInParent().getHeight();

        AnchorPane.setLeftAnchor(nodo, centerX - nodoWidth / 2);
        AnchorPane.setTopAnchor(nodo, centerY - nodoHeight / 2);
    }
    public void setAlmacenes(LinkedList<Vertice <Almacen> > almacenes) {
        Almacenes = almacenes;
    }
    String RecorrerR (Node <Vertice <Almacen> > aux){
        if(aux != null){
            CrearNodo(aux.getData().getAlmacen());
            System.out.println(aux);
            RecorrerR(aux.getNextNode());
        }
        else 
            return "-- Se termino --";
        return "-- En Proceso --";
    }
}
