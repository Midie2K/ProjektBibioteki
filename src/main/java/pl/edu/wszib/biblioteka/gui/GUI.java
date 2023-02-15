package pl.edu.wszib.biblioteka.gui;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.biblioteka.bsk.UserBSK;
import pl.edu.wszib.biblioteka.data.BookDB;
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
        System.out.println("3. Logout");
        System.out.println("4. Exit");
        if (authenticator.loggedUser != null &&
                authenticator.loggedUser.getRole().equals(Role.ADMIN)) {
            System.out.println("5. Add book");
            System.out.println("6. List users");
        }
        return scanner.nextLine();
    }

    public static String search(){
        System.out.println("1. Search by title");
        System.out.println("2. Search by author");

        return scanner.nextLine();
    }

    public static void listBooks() {
        BookDB bookDB = BookDB.getInstance();
        System.out.println("Title\t | \tAuthor\t | \tISBN");
        bookDB.getBooks();
        System.out.println("\n");
    }

    public static void listUsers(){
        UserBSK userBSK = UserBSK.getInstance();
        System.out.println("Login\t | \tRole");
        userBSK.listUsers(UserBSK.getAllUsers());
        System.out.println("\n");
    }

    public static String readName() {
        System.out.println("Book title:");
        return scanner.nextLine();
    }
    public static String readAuthor() {
        System.out.println("Book author:");
        return scanner.nextLine();
    }
    public static String readIsbn() {
        System.out.println("Book isbn:");
        return scanner.nextLine();
    }
    public static String readUser(){
        System.out.println("User name:");
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
    public static void showRegisterResult(boolean result) {
        if (result) {
            System.out.println("Registered successful\n");
        } else {
            System.out.println("Login is taken, try new login\n");
        }
    }
}
