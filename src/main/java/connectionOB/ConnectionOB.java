package connectionOB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOB {

    private static ConnectionOB connectionOB;
    private Connection connection;
    private ConnectionOB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost/logindatabase","root","7392");
    }
    public Connection getConnection(){

        return connection;

    }
    public static ConnectionOB getInstance() throws ClassNotFoundException, SQLException{
        if(connectionOB==null){

            connectionOB =new ConnectionOB();
        }
        return connectionOB;
    }
}
