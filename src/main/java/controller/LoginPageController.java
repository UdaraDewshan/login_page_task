package controller;

import connectionOB.LoginDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
    void linkSingUpAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SingUpPage.fxml"))));
        Stage stage1 = (Stage) linkSingUp.getScene().getWindow();
        stage1.close();
        stage.show();
        stage.setTitle("Sing Up");
    }

    @FXML
    void btnSingInAction(ActionEvent event) {

        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();

        if (!email.isEmpty() && !password.isEmpty()){

            try {
                ArrayList<Customer> emailArray= LoginDetailsController.getLoginDetails();
                for (Customer customer : emailArray){
                    if(email.equals(customer.getEmail())){
                        if (BCrypt.checkpw(password,customer.getPassword())){
                            JOptionPane.showMessageDialog(null, "Login Successful!");
                            loadDashboard();
                            return;
                        }else {
                            JOptionPane.showMessageDialog(null, "Password does not match");
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "email does not match");


            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException();
            }

        }else {
            JOptionPane.showMessageDialog(null,"Fields are empty");
        }
    }


    public static void loadDashboard(){

    }



}
