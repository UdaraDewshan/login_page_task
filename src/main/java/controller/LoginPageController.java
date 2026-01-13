package controller;

import connectionOB.LoginDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        Stage currentStage = (Stage) linkSingUp.getScene().getWindow();
        currentStage.close();
        stage.show();
        stage.setTitle("Sign Up");
    }

    @FXML
    void btnSingInAction(ActionEvent event) {

        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();

        if (!email.isEmpty() && !password.isEmpty()){

            try {
                ArrayList<Customer> emailArray = LoginDetailsController.getLoginDetails();
                boolean isFound = false;

                for (Customer customer : emailArray){
                    if(email.equals(customer.getEmail())){
                        if (BCrypt.checkpw(password, customer.getPassword())){

                            isFound = true;
                            JOptionPane.showMessageDialog(null, "Login Successful!");

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardPage.fxml"));

                            Parent root = loader.load();

                            DashboardController dashboardController = loader.getController();


                            String firstName = LoginDetailsController.getFirstName(email);
                            dashboardController.setUserInfo(firstName);

                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));

                            Stage currentStage = (Stage) btnSingIn.getScene().getWindow();
                            currentStage.close();
                            stage.show();

                            return;

                        } else {
                            JOptionPane.showMessageDialog(null, "Password does not match");
                            return;
                        }
                    }
                }

                if(!isFound){
                    JOptionPane.showMessageDialog(null, "Email does not match");
                }

            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Fields are empty");
        }
    }
}