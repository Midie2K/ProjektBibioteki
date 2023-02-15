package pl.edu.wszib.biblioteka.bsk;

import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBSK {
    private static Connection connection;
    private static final UserBSK instance = new UserBSK();

    private UserBSK(){
        this.connection = ConnectionBSK.getConnection();
    }
    public static UserBSK getInstance(){
        return instance;
    }

    public static boolean addUser(User user){
       if(!findUserByLogin(user)){
           try{
               String sql = "INSERT INTO users(login, passwd, role) VALUES (?,?,?)";

               PreparedStatement statement = connection.prepareStatement(sql);
               statement.setString(1, user.getLogin());
               statement.setString(2, user.getPasswd());
               statement.setString(3,user.getRole().toString());

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
                        Role.valueOf(rs.getString("role"))
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
                        Role.valueOf(rs.getString("role"))
                );
            } else
                return result = null;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
