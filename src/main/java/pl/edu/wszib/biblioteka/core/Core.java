package pl.edu.wszib.biblioteka.core;

import pl.edu.wszib.biblioteka.data.BookDB;
import pl.edu.wszib.biblioteka.data.UserDB;
import pl.edu.wszib.biblioteka.gui.GUI;
import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;

public class Core {
    public static void start(){
        final BookDB bookDB = BookDB.getInstance();
        final UserDB userDB = UserDB.getInstance();
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
                        boolean registred = false;
                        do{
                            user=gui.readLoginAndPasswd();
                            if(!userDB.ifUserExist(user.getLogin())){
                                registred = true;
                            }
                            gui.showRegisterResult(registred);
                        }while(!registred);
                        userDB.register(user);
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
                                bookDB.getBooksByName(gui.readName());
                                break;
                            case "2":
                                bookDB.getBooksByAuthor(gui.readAuthor());
                                break;
                            default:
                                System.out.println("Wrong choose !!");
                                break;
                        }

                        break;
                    case "3":
                        isRunning = false;
                        isLoged = false;
                        counter = 0;
                        authenticator.loggedUser = null;
                        break;
                    case "4":
                        isRunning = false;
                        Exit = true;
                        break;
                    case "5":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)) {
                            gui.showAddResult(bookDB.addBook(gui.readName(), gui.readAuthor(), gui.readIsbn()));
                        }
                        break;
                    case "6":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)){
                            gui.listUsers();
                        }
                        break;
                   // case "7":
                   //     if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)){
                   //         gui.showGrantResult(userDB.grantUser(gui.readUser()));
                   //     }
                  //      break;
                    default:
                        System.out.println("Wrong choose !!");
                        break;
                }
            }
        }
    }
}
