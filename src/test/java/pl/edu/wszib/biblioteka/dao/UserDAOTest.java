package pl.edu.wszib.biblioteka.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;

public class UserDAOTest {

    @Test
    public void SuccessedGetUserbyLogin(){
        UserDAO userDAO = UserDAO.getInstance();
        User expectedResult = new User(1,"admin",
                "9a58915b0de3f2525c5cf2193903b9fd",
                Role.ADMIN,"","");
        User actual = userDAO.getUserByLogin("admin");
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
    public void FailedGetUserbyLogin(){
        UserDAO userDAO = UserDAO.getInstance();
        User expectedResult = null;
        User actual = userDAO.getUserByLogin("Adminek");

        Assertions.assertSame(expectedResult,actual);
    }

    @Test
    public void SuccessedGetUserbyId(){
        UserDAO userDAO = UserDAO.getInstance();
        User expectedResult = new User(1,"admin",
                "9a58915b0de3f2525c5cf2193903b9fd",
                Role.ADMIN,"","");

        User actual = userDAO.getUserByid(1);
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
    public void FailedGetUserbyId(){
        UserDAO userDAO = UserDAO.getInstance();
        User expectedResult = null;
        User actual = userDAO.getUserByid(0);

        Assertions.assertSame(expectedResult,actual);
    }

    @Test
    public void SuccessedGetUserbyIdString(){
        UserDAO userDAO = UserDAO.getInstance();
        User expectedResult = new User(1,"admin",
                "9a58915b0de3f2525c5cf2193903b9fd",
                Role.ADMIN,"","");

        User actual = userDAO.getUserByid("1");
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
    public void FailedGetUserbyIdString(){
        UserDAO userDAO = UserDAO.getInstance();
        User expectedResult = null;
        User actual = userDAO.getUserByid("0");

        Assertions.assertSame(expectedResult,actual);
    }
}
