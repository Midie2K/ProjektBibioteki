package pl.edu.wszib.biblioteka.dao;

import pl.edu.wszib.biblioteka.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static Connection connection;
    private static final BookDAO instance = new BookDAO();
    private static final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private BookDAO(){
        this.connection = connectionDAO.getConnection();
    }
    public static BookDAO getInstance(){
        return instance;
    }

    public static boolean addBook(Book book){
        if(!findBookByTitle(book)){
            try{
                String sql = "INSERT INTO books(title, author, isbn, available) VALUES (?,?,?,?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getIsbn());
                statement.setInt(4, 1);

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return true;
        }
        else
            return false;
    }
    public static boolean findBookByTitle(Book book) {
        try {
            String sql = "SELECT * FROM books WHERE title = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Book> getAllBooks(){
        List<Book> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Books";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        (rs.getInt("available")==1)?true:false
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static void listBook(List<Book> list) {
        System.out.println(list.stream().toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));
    }

        public void getBooksByTitle(String title,List<Book> books) {
            System.out.println("Id\t | \tTitle\t | \tAuthor\t | \tISBN\t | \tAvailable");
            System.out.println(books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .toList().
                    toString().replace("[","").
                    replace("]","").
                    replace(", ","\n"));;
        }

    public void getBooksByAuthor(String author,List<Book> books) {
        System.out.println("Id\t | \tTitle\t | \tAuthor\t | \tISBN\t | \tAvailable");
        System.out.println(books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));;
    }
    public void getBooksByISBN(String isbn,List<Book> books) {
        System.out.println("Id\t | \tTitle\t | \tAuthor\t | \tISBN\t | \tAvailable");
        System.out.println(books.stream()
                .filter(book -> book.getIsbn().contains(isbn))
                .toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));;
    }


}
