package pl.edu.wszib.biblioteka.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionDAOTest {
    @Test
    public void SuccessfulConnectionTest(){
        ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
        final String url = "jdbc:mysql://localhost:3306/bibiotekapp";
        final String user = "root";
        final String password = "";

        Assertions.assertDoesNotThrow(() ->connectionDAO.connect(url,user,password));
    }
    @Test
    public void BadUrlConnectionTest(){
        ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
        String url = "jdbc:mysql://localhost:3306/BadBDName";
        String user = "root";
        String password = "";

        Assertions.assertThrows(RuntimeException.class ,
                () ->connectionDAO.connect(url,user,password));
    }
    @Test
    public void BadUserConnectionTest(){
        ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
        String url = "jdbc:mysql://localhost:3306/BadBDName";
        String user = "BadUser";
        String password = "";

        Assertions.assertThrows(RuntimeException.class ,
                () ->connectionDAO.connect(url,user,password));
    }
    @Test
    public void BadPasswordConnectionTest(){
        ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
        String url = "jdbc:mysql://localhost:3306/BadBDName";
        String user = "root";
        String password = "badPassword";

        Assertions.assertThrows(RuntimeException.class ,
                () ->connectionDAO.connect(url,user,password));
    }

    @Test
    public void SuccessfulGetConnectionTest(){
        ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
        Connection actual = connectionDAO.getConnection();

        Assertions.assertTrue(actual instanceof Connection);

    }

}
