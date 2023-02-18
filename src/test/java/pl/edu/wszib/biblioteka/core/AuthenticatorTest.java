package pl.edu.wszib.biblioteka.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.biblioteka.dao.UserDAO;
import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;

public class AuthenticatorTest {
    @Test
    public void SuccessfulAuthenticatedUser(){
        Authenticator authenticator = Authenticator.getIstance();
        UserDAO userDAO = UserDAO.getInstance();
        User test = new User();
        test.setLogin("admin");
        test.setPasswd("9a58915b0de3f2525c5cf2193903b9fd");

        User expectedResult = userDAO.getUserByLogin("admin");

        authenticator.authenticate(test);
        User actual = authenticator.loggedUser;
        boolean comparedData = false;
        if(expectedResult.getId() == actual.getId() &&
                expectedResult.getLogin().equals(actual.getLogin()) &&
                expectedResult.getPasswd().equals(actual.getPasswd()) &&
                expectedResult.getRole() == actual.getRole() &&
                expectedResult.getName().equals(actual.getName()) &&
                expectedResult.getSurname().equals(actual.getSurname())){
            comparedData = true;
        }
        Assertions.assertTrue(comparedData);
    }
    @Test
    public void FailedAuthenticatedUser(){
        Authenticator authenticator = Authenticator.getIstance();
        UserDAO userDAO = UserDAO.getInstance();
        User test = new User();
        test.setLogin("admin");
        test.setPasswd("badPassword");
        User expectedResult = userDAO.getUserByLogin("admin");

        authenticator.authenticate(test);
        User actual = authenticator.loggedUser;

        Assertions.assertNotEquals(expectedResult,actual);

    }
}
