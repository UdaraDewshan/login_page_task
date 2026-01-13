package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button btnLogOut;

    @FXML
    private Text lblWelcome;


    public void setUserInfo(String firstName) {
        if (firstName != null) {
            lblWelcome.setText("Welcome " + firstName);
        } else {
            lblWelcome.setText("Welcome User");
        }
    }

    @FXML
    void btnLogOutAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"))));

        Stage currentStage = (Stage) btnLogOut.getScene().getWindow();
        currentStage.close();
        stage.show();
    }
}