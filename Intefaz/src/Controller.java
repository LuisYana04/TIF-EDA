import Estructuras.AlmacenGrafo.Grafo;
import Estructuras.AlmacenGrafo.Vertice;
import Estructuras.LINKEDLIST.LinkedList;
import Estructuras.LINKEDLIST.Node;
import Objetos.Almacen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Controller {

    private Grafo <Almacen> Almacenes = new Grafo<Almacen>();

    private LinkedList <RadioButton> Seleccion = new LinkedList<RadioButton>();

    @FXML
    private ToggleGroup toggleGroup;

     @FXML
    private VBox Campo;

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
        Almacen aux = new Almacen<String> (CodigoA.getText(),NombreA.getText(),DireccionA.getText());
        if(Almacenes.insertVertice(aux)){
            RellenarLista(aux,CodigoA.getText(),NombreA.getText(),DireccionA.getText(),null);
        }
    }

    @FXML
    void Borrar(ActionEvent event) {
        //this.Almacenes.removeVertex(null);
        Node<RadioButton> aux = Seleccion.getRoot();
        while(aux != null){
            System.out.println("$$");
            if(aux.getData().isSelected()){
                System.out.println("%%");
                //Seleccion.remove(aux.getData());
                Almacen data_aux = (Almacen) aux.getData().getUserData();
                System.out.println(data_aux.toString());
                aux = aux.getNextNode();
                Almacenes.removeVertex(data_aux);
                Reset();

                //Reset();
            }
            else
                aux = aux.getNextNode();
        }
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
        Reset();
    }


    void RellenarLista (Almacen Alm,String Code, String Nombre, String Direccion, String Productos) {
        RadioButton radioButton = new RadioButton("-------\n"+Code);
        radioButton.setUserData(Alm);
        radioButton.setId(Code);
        Seleccion.insertLast(radioButton);
        Label productoLabel = new Label("-------\n"+">Mesa\n>Silla");
        Label nombreLabel = new Label("-------\n"+Nombre);
        Label direccionLabel = new Label("-------\n"+Direccion);
        int numRows = Tabla.getRowCount();
        Tabla.addRow(numRows, radioButton,nombreLabel, direccionLabel, productoLabel);
        System.out.println("-->"+Tabla.getRowCount());
    }

    void RellenarLista () {
        Node<Vertice<Almacen>> aux = Almacenes.getRoot().getRoot();
        while(aux != null){
            Almacen data = aux.getData().getAlmacen();
            RellenarLista(data,data.getCodigo(), data.getNombre(), data.getDireccion(), null);
            aux = aux.getNextNode();
        }
    }

    void Limpia() {
    Seleccion = new LinkedList<RadioButton>();
    int numRows = Tabla.getRowCount();
    Tabla.getChildren().removeIf(node -> {
        Integer rowIndex = GridPane.getRowIndex(node);
        // Verificar si el índice de fila no es nulo y está dentro del rango a eliminar.
        return rowIndex != null && rowIndex >= 2 && rowIndex <= numRows;
    });
}

    public void setAlmacenes(Grafo<Almacen> almacenes) {
        Almacenes = almacenes;
    }

    void Reset(){
        Limpia();
        RellenarLista();
    }
}