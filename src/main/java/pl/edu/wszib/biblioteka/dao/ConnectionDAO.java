package pl.edu.wszib.biblioteka.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDAO {
    private static final ConnectionDAO instance = new ConnectionDAO();
    private static Connection connection;

    public static ConnectionDAO getInstance(){
        return instance;
    }
    private ConnectionDAO(){
    }

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

