import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private PasswordField Password;

    @FXML
    private TextField User;

    @FXML
    private Button login;

    @FXML
    void Ingreso(ActionEvent event) {
        String usuario = User.getText();
        String clave = Password.getText();
        if(usuario.equals("el mariana") && clave.equals("247"))
            System.out.println("Ingreso Correcto");
        else
            System.out.println("ERROR 404");
    }

}
