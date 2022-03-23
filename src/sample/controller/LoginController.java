package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.model.UserDAO;
import sample.utils.BCryptPassword;

public class LoginController {
    @FXML
    private TextField text_id;
    @FXML
    private PasswordField pass_id;
    @FXML
    private Label error_label;

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws Exception {
        //TODO: Savarankiškai padaryti FrontEnd validacija su prisijungimo vardu ir slaptažodžiu.


        String bCryptPassword = UserDAO.getBCryptPassword(text_id.getText());


        System.out.println("Slaptažodis:" + bCryptPassword);
        System.out.println("Slaptažodis(Atkoduotas):" + pass_id.getText());

        if (bCryptPassword.equals("")) { //Jeigu tuščias vadinasi nerado(DB)
            error_label.setVisible(true);
            error_label.setText("Nerastas toks prisijungimo vardas arba slaptažodis(DB)");
        } else { //Jeigu netuščias , vadinasi kažką rado
            boolean isValidPassword = BCryptPassword.checkPassword(pass_id.getText(), bCryptPassword);
            if (isValidPassword) {
                Parent root = FXMLLoader.load(Main.class.getResource("view/dashboard-view.fxml"));
                Stage loginStage = new Stage();
                loginStage.setTitle("Dashboard");
                loginStage.setScene(new Scene(root, 1131, 810));
                loginStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                error_label.setVisible(true);
                error_label.setText("Sėkmingai įvestas prisijungimo vardas arba slaptažodis(DB)");
            } else {
                error_label.setVisible(true);
                error_label.setText("Klaidingai įvestas prisijungimo vardas arba slaptažodis(DB)");
            }
        }
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws Exception {

        //Vaizdo užkrovimas
        Parent root = FXMLLoader.load(Main.class.getResource("view/register-view.fxml"));
        Stage registerStage = new Stage();
        //Stage (langas) bus vienas, scenų gali būti daug
        registerStage.setTitle("Registracijos langas");
        registerStage.setScene(new Scene(root, 600, 400));
        //Parodymas lango
        registerStage.show();
        //Kodas reikalingas paslėpti prieš tai buvusį langą
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}