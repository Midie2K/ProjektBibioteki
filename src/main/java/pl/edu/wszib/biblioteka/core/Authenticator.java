package pl.edu.wszib.biblioteka.core;

import pl.edu.wszib.biblioteka.dao.UserDAO;
import pl.edu.wszib.biblioteka.model.User;

public class Authenticator {
    private static final Authenticator instance = new Authenticator();
    public User loggedUser = null;
    public final String seed = "@123LubiePlacki123@";

    private Authenticator(){}
    public void authenticate(User user) {
        UserDAO userDAO = UserDAO.getInstance();
        String login = user.getLogin();
        User userFromDB = userDAO.getUserByLogin(login);

        if(userFromDB != null &&
                userFromDB.getPasswd().equals(
                        (user.getPasswd()))) {
            loggedUser = userFromDB;
        }
    }

    public static Authenticator getIstance() {
        return instance;
    }
}
