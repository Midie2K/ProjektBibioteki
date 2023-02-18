package pl.edu.wszib.biblioteka.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void Connect2Users(){
        User expectedResult = new User(0,"a","a",Role.USER,"a","a");
        User u1 = new User();
        User u2 = new User();
        u1.setLogin("a");
        u1.setPasswd("a");
        u2.setName("a");
        u2.setSurname("a");
        User actual = User.connectLoginAndName(u1,u2);

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
}
