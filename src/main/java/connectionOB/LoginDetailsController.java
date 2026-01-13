package connectionOB;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
