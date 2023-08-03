import Estructuras.AlmacenGrafo.Grafo;
import Estructuras.AlmacenGrafo.Vertice;
import Objetos.Almacen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Controller {
    private Grafo <String> Almacenes = new Grafo<String>();
    @FXML
    private Text Historial;

    @FXML
    private GridPane Tabla;

    @FXML
    private ImageView UpdateI;

    @FXML
    private RadioButton codigo;

    @FXML
    private Label direccion;

    @FXML
    private Label nombre;

    @FXML
    private Text productos;

    @FXML
    void Agregar(ActionEvent event) {
        this.Almacenes.insertVertice("Arequipa");
        System.out.println(Almacenes);
    }

    @FXML
    void Borrar(ActionEvent event) {
        this.Almacenes.removeVertex(null);
    }

    @FXML
    void Cambiar(ActionEvent event) {
        Vertice nuevo = null;
        Vertice aux = Almacenes.getVertex(null);
        aux = nuevo;
    }

    @FXML
    void Editar(ActionEvent event) {
      Vertice aux;
      if(Almacenes.searchVertex(null))
        aux = Almacenes.getVertex(null);
      else
        System.out.println("ERROR 404");
    }

    @FXML
    void Update(ActionEvent event) {
        
    }


    public void setAlmacenes(Grafo<String> almacenes) {
        Almacenes = almacenes;
    }
    
}
