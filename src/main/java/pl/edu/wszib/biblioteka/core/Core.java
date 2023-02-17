package pl.edu.wszib.biblioteka.core;

import pl.edu.wszib.biblioteka.dao.BookDAO;
import pl.edu.wszib.biblioteka.dao.RentingDAO;
import pl.edu.wszib.biblioteka.dao.UserDAO;
import pl.edu.wszib.biblioteka.gui.GUI;
import pl.edu.wszib.biblioteka.model.Book;
import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;

public class Core {
    public static void start(){
        final BookDAO bookDAO = BookDAO.getInstance();
        final UserDAO userDAO = UserDAO.getInstance();
        final RentingDAO rentingDAO = RentingDAO.getInstance();
        final Authenticator authenticator = Authenticator.getIstance();
        final GUI gui = GUI.getInstance();
        boolean isRunning = false;
        boolean isLoged = false;
        boolean Exit = false;
        int counter = 0;

        while(!Exit) {
            while (!isLoged) {
                switch (gui.login()) {
                    case "1":
                        User user;
                        user = User.connectLoginAndName(gui.readLoginAndPasswd(), gui.readNameAndSurname());
                        gui.showRegisterResult(userDAO.addUser(user));
                        break;
                    case "2":
                        while (!isRunning && counter < 3) {
                            authenticator.authenticate(gui.readLoginAndPasswd());
                            isRunning = authenticator.loggedUser != null;
                            isLoged = true;
                            if (!isRunning) {
                                System.out.println("Not authorized !!");
                            }
                            counter++;
                            if(counter==3){
                                Exit=true;
                            }
                        }
                        break;
                    default:
                        System.out.println("Wrong choose !!");
                        break;
                }
            }

            while (isRunning) {
                switch (gui.showMenu()) {
                    case "1":
                        gui.listBooks();
                        break;
                    case "2":
                        switch (gui.search()){
                            case "1":
                                bookDAO.getBooksByTitle(gui.readTitle(),bookDAO.getAllBooks());
                                break;
                            case "2":
                                bookDAO.getBooksByAuthor(gui.readAuthor(),bookDAO.getAllBooks());
                                break;
                            case "3":
                                bookDAO.getBooksByISBN(gui.readIsbn(),bookDAO.getAllBooks());
                                break;
                            default:
                                System.out.println("Wrong choose !!");
                                break;
                        }

                        break;
                    case "3":
                        gui.showRentResult(rentingDAO.rentBook(authenticator.loggedUser,bookDAO.getBookByid(gui.readBookId())));
                        break;
                    case "4":
                        gui.listRentedByUser();
                        rentingDAO.listRentedByUser(authenticator.loggedUser,rentingDAO.allRentedBooks());
                        gui.showReturnResult(rentingDAO.returnBook(gui.readRentId(), authenticator.loggedUser));
                        break;
                    case "5":
                        isRunning = false;
                        isLoged = false;
                        counter = 0;
                        authenticator.loggedUser = null;
                        break;
                    case "6":
                        isRunning = false;
                        Exit = true;
                        break;
                    case "7":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)) {
                            Book book = new Book();
                            book.setTitle(gui.readTitle());
                            book.setAuthor(gui.readAuthor());
                            book.setIsbn(gui.readIsbn());
                            gui.showAddResult(bookDAO.addBook(book));
                        }
                        break;
                    case "8":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)){
                            gui.listUsers();
                        }
                        break;
                    case "9":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)){
                            gui.listRentedBooks();
                        }
                        break;
                    case "0":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)){
                            gui.listExceededBooks();
                        }
                        break;

                    default:
                        System.out.println("Wrong choose !!");
                        break;
                }
            }
        }
    }
}
