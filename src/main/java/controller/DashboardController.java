package controller;

import connectionOB.LoginDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text; // Text import කරන්න අමතක කරන්න එපා
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button btnLogOut;

    @FXML
    private Text lblWelcome;


    public void setUserInfo(String firstName) {

        lblWelcome.setText("Welcome " + firstName);
    }

    @FXML
    void btnLogOutAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"))));
        Stage stage2= (Stage) btnLogOut.getScene().getWindow();
        stage2.close();
        stage.show();
    }

}