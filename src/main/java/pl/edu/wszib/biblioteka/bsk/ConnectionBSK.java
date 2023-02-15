package pl.edu.wszib.biblioteka.bsk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionBSK {
    public static Connection connection;
    public static void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bibiotekapp",
                    "root",
                    "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        connect();
        return connection;
    }

}

