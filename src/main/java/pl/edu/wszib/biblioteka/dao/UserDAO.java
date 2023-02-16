package pl.edu.wszib.biblioteka.dao;

import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static Connection connection;
    private static final UserDAO instance = new UserDAO();
    private static final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private UserDAO(){
        this.connection = connectionDAO.getConnection();
    }
    public static UserDAO getInstance(){
        return instance;
    }

    public static boolean addUser(User user){
       if(!findUserByLogin(user)){
           try{
               String sql = "INSERT INTO users(login, passwd, role, name, surname) VALUES (?,?,?,?,?)";

               PreparedStatement statement = connection.prepareStatement(sql);
               statement.setString(1, user.getLogin());
               statement.setString(2, user.getPasswd());
               statement.setString(3,user.getRole().toString());
               statement.setString(4, user.getName());
               statement.setString(5, user.getSurname());

               statement.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

           return true;
       }
       else
           return false;
    }
    public static boolean findUserByLogin(User user) {
        try {
            String sql = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<User> getAllUsers(){
        List<User> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.add(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("passwd"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("name"),
                        rs.getString("surname")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static void listUsers(List<User> list) {
        System.out.println(list.stream().toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));
    }

    public static User getUserByLogin(String login){
        User result;
        try{
            String sql = "SELECT * FROM users WHERE login = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return result = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("passwd"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("name"),
                        rs.getString("surname")
                );
            } else
                return result = null;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
