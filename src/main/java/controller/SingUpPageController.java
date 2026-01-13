package controller;

import connectionOB.ConnectionOB;
import connectionOB.LoginDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SingUpPageController {

    @FXML
    private Button btnBackToLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblMessage;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRePassword;

    @FXML
    void btnBackToLoginAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"))));
        Stage stage2= (Stage) btnBackToLogin.getScene().getWindow();
        stage2.close();
        stage.show();
    }

    @FXML
    void btnRegisterAction(ActionEvent event) {
        String email = txtEmail.getText().trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String password = txtPassword.getText();
        String passwordReEnter = txtRePassword.getText();


        if (!email.isBlank() && !firstName.isBlank() && !lastName.isBlank() && !password.isEmpty() && !passwordReEnter.isBlank()) {
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
            Connection connection = null;
            if (password.matches(regex)) {
                if (email.toLowerCase().endsWith("@gmail.com")){
                    String EncryptPass= org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt(12));

                    try {
                        connection = ConnectionOB.getInstance().getConnection();
                        Customer customer = new Customer(email,firstName,lastName,EncryptPass);
                        boolean state = LoginDetailsController.addCustomer(customer);

                        if (state){
                            JOptionPane.showMessageDialog(null,"You have signed up successfully ");
                        }else {
                            JOptionPane.showMessageDialog(null,"Oops! Something went wrong. Please try again..!");
                        }

                        connection.setAutoCommit(true);

                    } catch (ClassNotFoundException | SQLException ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }


                }else{
                    JOptionPane.showMessageDialog(null,"Please enter a valid Gmail address (must end with @gmail.com)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Password is weak. Needs 8 chars, Upper, Lower & Symbol.");
            }
        }else{
            JOptionPane.showMessageDialog(null,"empty field");
        }
    }

}
