package pl.edu.wszib.biblioteka.gui;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.biblioteka.dao.BookDAO;
import pl.edu.wszib.biblioteka.dao.RentingDAO;
import pl.edu.wszib.biblioteka.dao.UserDAO;
import pl.edu.wszib.biblioteka.core.Authenticator;
import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;

import java.util.Scanner;

public class GUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GUI instance = new GUI();

    private GUI(){}

    public static GUI getInstance(){
        return instance;
    }

    public static String login(){
        System.out.println("1. Register");
        System.out.println("2. Login");
        return scanner.nextLine();
    }
    public static String showMenu() {
        Authenticator authenticator = Authenticator.getIstance();
        System.out.println("1. List Books");
        System.out.println("2. Search Books");
        System.out.println("3. Rent Book");
        System.out.println("4. Return Book");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        if (authenticator.loggedUser != null &&
                authenticator.loggedUser.getRole().equals(Role.ADMIN)) {
            System.out.println("7. Add book");
            System.out.println("8. List users");
            System.out.println("9. List rented books");
            System.out.println("0. List exceeded books");
        }
        return scanner.nextLine();
    }

    public static String search(){
        System.out.println("1. Search by title");
        System.out.println("2. Search by author");
        System.out.println("3. Search by ISBN");

        return scanner.nextLine();
    }

    public static void listBooks() {
        BookDAO bookDAO = BookDAO.getInstance();
        System.out.println("Id\t | \tTitle\t\t\t | \tAuthor\t\t | \tISBN\t | \tAvailable\t | \tDateOfRent\t | \tEndOfRent");
        bookDAO.listBook(bookDAO.getAllBooks());
        System.out.println("\n");
    }

    public static void listUsers(){
        UserDAO userDAO = UserDAO.getInstance();
        System.out.println("Id \t | \tLogin\t | \tName\t | \tSurname");
        userDAO.listUsers(UserDAO.getAllUsers());
        System.out.println("\n");
    }
    public static void listRentedBooks(){
        RentingDAO rentingDAO = RentingDAO.getInstance();
        System.out.println("Rent id | \ttitle\t | \tauthor\t\t\t | \tisbn\t | \tname" +
                            "\t | \tsurname\t | \tDate of rent\t | \tEnd of rent");
        rentingDAO.listAllRented(rentingDAO.allRentedBooks());
        System.out.println("\n");
    }
    public void listExceededBooks() {
        RentingDAO rentingDAO = RentingDAO.getInstance();
        System.out.println("Rent id | \ttitle\t | \tauthor\t\t\t | \tisbn\t | \tname" +
                "\t | \tsurname\t | \tDate of rent\t | \tEnd of rent");
        rentingDAO.listExceededBooks(rentingDAO.allRentedBooks());
        System.out.println("\n");
    }

    public void listRentedByUser(){
        System.out.println("Rent id | \ttitle\t | \tauthor\t\t\t | \tisbn\t | \tname" +
                "\t | \tsurname\t | \tDate of rent\t | \tEnd of rent");
    }


    public String readTitle() {
        System.out.println("Book title:");
        return scanner.nextLine();
    }
    public String readAuthor() {
        System.out.println("Book author:");
        return scanner.nextLine();
    }
    public String readIsbn() {
        System.out.println("Book isbn:");
        return scanner.nextLine();
    }
    public  String readBookId() {
        System.out.println("Book id:");
        return scanner.nextLine();
    }
    public String readRentId(){
        System.out.println("Rent id:");
        return scanner.nextLine();
    }


    public static void showAddResult(boolean result) {
        if (result) {
            System.out.println("Book successful added\n");
        } else {
            System.out.println("Book already exist\n");
        }
    }


    public static User readLoginAndPasswd() {
        Authenticator authenticator = Authenticator.getIstance();
        User user = new User();
        System.out.println("Login:");
        user.setLogin(scanner.nextLine());
        System.out.println("Password:");
        user.setPasswd(DigestUtils.md5Hex(scanner.nextLine() + authenticator.seed));
        return user;
    }

    public static User readNameAndSurname() {
        User user = new User();
        System.out.println("Name:");
        user.setName(scanner.nextLine());
        System.out.println("Surname:");
        user.setSurname(scanner.nextLine());
        return user;
    }

    public static void showRegisterResult(boolean result) {
        if (result) {
            System.out.println("Registered successful\n");
        } else {
            System.out.println("Login is taken, try new login\n");
        }
    }

    public static void showRentResult(boolean result) {
        if (result) {
            System.out.println("Book rented successful\n");
        } else {
            System.out.println("Bad book id or book is unavailable\n");
        }
    }
    public static void showReturnResult(boolean result) {
        if (result) {
            System.out.println("Book returned successful\n");
        } else {
            System.out.println("Bad rent id\n");
        }
    }
}
