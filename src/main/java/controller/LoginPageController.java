package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button btnSingIn;

    @FXML
    private Hyperlink linkSingUp;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnSingInAction(ActionEvent event) {

    }

    @FXML
    void linkSingUpAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SingUpPage.fxml"))));
        Stage stage1 = (Stage) linkSingUp.getScene().getWindow();
        stage1.close();
        stage.show();
        stage.setTitle("Sing Up");
    }

}
