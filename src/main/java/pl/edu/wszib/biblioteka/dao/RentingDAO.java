package pl.edu.wszib.biblioteka.dao;

import pl.edu.wszib.biblioteka.model.Book;
import pl.edu.wszib.biblioteka.model.Renting;
import pl.edu.wszib.biblioteka.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class RentingDAO {
    private static Connection connection;
    private static final RentingDAO instance = new RentingDAO();
    private static final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
    private static final BookDAO bookDAO = BookDAO.getInstance();
    private static final UserDAO userDAO = UserDAO.getInstance();



    private RentingDAO(){
        this.connection = connectionDAO.getConnection();
    }
    public static RentingDAO getInstance(){
        return instance;
    }

    public boolean rentBook(User user, Book book){
        if(book != null && book.getAvailable()){
            try{
                LocalDate today = LocalDate.now();
                LocalDate returnDate = today.plusDays(14);
                String sql = "INSERT INTO renting(user_id, book_id, dateOfRent, endOfRent) VALUES (?,?,?,?);";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, user.getId());
                statement.setInt(2, book.getId());
                statement.setDate(3, Date.valueOf(today.format(DateTimeFormatter.ISO_LOCAL_DATE)));
                statement.setDate(4, Date.valueOf(returnDate.format(DateTimeFormatter.ISO_LOCAL_DATE)));
                statement.executeUpdate();
                bookDAO.changetoUnavailable(book.getId());
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return true;
        }
        else
            return false;
    }

    public List<Renting> allRentedBooks() {
        List<Renting> renting = new ArrayList<>();
        try {
            String sql = "SELECT * FROM renting WHERE dateOfReturn IS NULL";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                renting.add(new Renting(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("dateOfRent"),
                        rs.getDate("endOfRent"),
                        userDAO.getUserByid( rs.getInt("user_id")),
                        bookDAO.getBookByid( rs.getInt("book_id"))

                ));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return renting;
    }
    public void listAllRented(List<Renting> list){
            System.out.println(list.stream().toList().
                    toString().replace("[","").
                    replace("]","").
                    replace(", ","\n"));
    }

    public void listExceededBooks(List<Renting> rentings){
        LocalDate today = LocalDate.now();
        System.out.println(rentings.stream()
                .filter(renting -> renting.getEndOfRent()
                                    .compareTo(Date.valueOf
                                    (today.format(DateTimeFormatter.ISO_LOCAL_DATE))) < 0)
                .toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));
    }

    public void listRentedByUser(User user, List<Renting> rentings){
        System.out.println(rentings.stream()
                .filter(renting -> renting.getUser_id()==user.getId())
                .toList()
                .toString().replace("[","").
                replace("]","").
                replace(", ","\n"));
    }

    public boolean returnBook(String id, User user){
        int rentId = Integer.valueOf(id);
        int bookId = getBookIdFromRenting(rentId,user);
        LocalDate today = LocalDate.now();
        if(bookId > 0){
            try{
                String sql = "UPDATE renting SET dateOfReturn = ? WHERE id = ? AND user_id = ? AND dateOfReturn IS NULL";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDate(1,Date.valueOf
                        (today.format(DateTimeFormatter.ISO_LOCAL_DATE)));
                statement.setInt(2,rentId);
                statement.setInt(3,user.getId());
                statement.executeUpdate();

                bookDAO.changetoAvailable(bookId);
                return true;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        else
            return false;
    }
    public int getBookIdFromRenting(int id, User user){
        int bookId;
        try{
            String sql = "SELECT book_id FROM renting WHERE id = ? AND user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setInt(2,user.getId());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                bookId = rs.getInt("book_id");
            }
            else
                bookId = -1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println();
        return bookId;
    }
    public void getDatesFromRentingByBookId(int id){
        Date rent;
        Date endOfRent;
        try{
            String sql="SELECT * FROM renting WHERE book_id = ? AND dateOfReturn IS NULL";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                rent = rs.getDate("dateOfRent");
                endOfRent = rs.getDate("endOfRent");
            }
            else{
                rent = null;
                endOfRent = null;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("\t | \t"+rent+"\t | \t"+endOfRent);
    }
}
