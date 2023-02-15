package pl.edu.wszib.biblioteka.core;

import pl.edu.wszib.biblioteka.bsk.UserBSK;
import pl.edu.wszib.biblioteka.model.User;

public class Authenticator {
    private static final Authenticator instance = new Authenticator();
    public User loggedUser = null;
    public final String seed = "@123LubiePlacki123@";

    private Authenticator(){}
    public void authenticate(User user) {
        UserBSK userBSK = UserBSK.getInstance();
        String login = user.getLogin();
        User userFromDB = userBSK.getUserByLogin(login);

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
