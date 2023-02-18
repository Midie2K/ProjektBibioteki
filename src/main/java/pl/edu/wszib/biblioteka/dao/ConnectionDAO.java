package pl.edu.wszib.biblioteka.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDAO {
    private static final String url = "jdbc:mysql://localhost:3306/bibiotekapp";
    private static final String user = "root";
    private static final String password = "";
    private static final ConnectionDAO instance = new ConnectionDAO();
    private static Connection connection;

    public static ConnectionDAO getInstance(){
        return instance;
    }
    private ConnectionDAO(){
    }

    public static void connect(String url, String user, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    url,
                    user,
                    password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        connect(url, user, password);
        return connection;
    }

}

