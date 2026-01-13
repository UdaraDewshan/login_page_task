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

    DashboardController dashboardController = new DashboardController();

    @FXML
    void linkSingUpAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SingUpPage.fxml"))));

        String firstName = LoginDetailsController.getFirstName(txtEmail.getText().trim());
        dashboardController.setUserInfo(firstName);

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
                            Stage stage=new Stage();
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardPage.fxml"))));
                            Stage stage2= (Stage) btnSingIn.getScene().getWindow();
                            stage2.close();
                            stage.show();



                            return ;
                        }else {
                            JOptionPane.showMessageDialog(null, "Password does not match");
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "email does not match");


            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            JOptionPane.showMessageDialog(null,"Fields are empty");
        }
    }
}
