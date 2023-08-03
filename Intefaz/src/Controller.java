import Estructuras.AlmacenGrafo.Grafo;
import Estructuras.AlmacenGrafo.Vertice;
import Objetos.Almacen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Controller {

    private Grafo <Almacen> Almacenes = new Grafo<Almacen>();

    @FXML
    private TextField Codigo;

    @FXML
    private TextField CodigoA;

    @FXML
    private TextField DireccionA;

    @FXML
    private TextField DireccionC;

    @FXML
    private Text Historial;

    @FXML
    private TextField NombreA;

    @FXML
    private TextField NombreC;

    @FXML
    private GridPane Tabla;

    @FXML
    private ImageView UpdateI;

    @FXML
    void AddProducto(ActionEvent event) {

    }

    @FXML
    void Agregar(ActionEvent event) {
        String codigo = CodigoA.getText();
        String Direccion = DireccionA.getText();
        String Nombre = NombreA.getText();
        Almacen aux = new Almacen<String> (codigo,Nombre,Direccion);
        if(Almacenes.insertVertice(aux)){
        RadioButton radioButton = new RadioButton("-------\n"+Almacenes.getVertex(aux).getCodigo());
        radioButton.setId("G-"+ Almacenes.getVertex(aux).getCodigo());
        Label productoLabel = new Label("-------\n"+">Mesa\n>Silla");
        Label nombreLabel = new Label("-------\n"+Almacenes.getVertex(aux).getNombre());
        Label direccionLabel = new Label("-------\n"+Almacenes.getVertex(aux).getDireccion());

        // Obtener el número actual de filas en el GridPane y agregar la nueva fila en la siguiente posición
        int numRows = Tabla.getRowCount();
        Tabla.addRow(numRows, radioButton,nombreLabel, direccionLabel, productoLabel);
        System.out.println("-->"+Tabla.getRowCount());
        }
    }

    @FXML
    void Borrar(ActionEvent event) {
        this.Almacenes.removeVertex(null);
    }

    @FXML
    void BorrarHistorial(ActionEvent event) {

    }

    @FXML
    void Cambiar(ActionEvent event) {
        Almacen nuevo = null;
        Almacen aux = Almacenes.getVertex(null);
        aux = nuevo;
    }

    @FXML
    void ChangeProducto(ActionEvent event) {

    }

    @FXML
    void Editar(ActionEvent event) {
      Almacen aux;
      if(Almacenes.searchVertex(null))
        aux = Almacenes.getVertex(null);
      else
        System.out.println("ERROR 404");
    }

    @FXML
    void Update(ActionEvent event) {
        
    }


    public void setAlmacenes(Grafo<Almacen> almacenes) {
        Almacenes = almacenes;
    }
    
}
