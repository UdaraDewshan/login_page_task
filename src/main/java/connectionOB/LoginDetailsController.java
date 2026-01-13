package connectionOB;

import controller.DashboardController;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class LoginDetailsController {

    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionOB.getInstance().getConnection();
        String sql="Insert into signup_details Values(?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(3,customer.getEmail());
        preparedStatement.setObject(1,customer.getFirstName());
        preparedStatement.setObject(2,customer.getLastName());
        preparedStatement.setObject(4,customer.getPassword());
        int i = preparedStatement.executeUpdate();
        return i>0;
    }

    public static ArrayList<Customer> getLoginDetails() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> emailArray=new ArrayList<>();
        String SQL="SELECT * FROM signup_details;";
        Connection connection=ConnectionOB.getInstance().getConnection();
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        while (rst.next()){
            emailArray.add(new Customer(rst.getString("email"),rst.getString("first_name"),rst.getString("last_name"),rst.getString("password")));
        }
        return emailArray;
    }


    public static String getFirstName(String email) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionOB.getInstance().getConnection();

        String SQL = "SELECT first_name FROM signup_details WHERE email = ?";
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setString(1, email);

        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            return rst.getString("first_name");
        }
        return "User";
    }
}
