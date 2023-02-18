package pl.edu.wszib.biblioteka.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.biblioteka.model.User;

public class RentingDAOTest {
    @Test
    public void SuccessfulGotBookIdFromRenting(){
        RentingDAO rentingDAO = RentingDAO.getInstance();
        User user = new User();
        user.setId(2);
        int expectedResult = 3;
        int actual = rentingDAO.getBookIdFromRenting(1,user);

        Assertions.assertEquals(expectedResult,actual);
    }
    @Test
    public void FailedGotBookIdFromRenting(){
        RentingDAO rentingDAO = RentingDAO.getInstance();
        User user = new User();
        user.setId(1);
        int expectedResult = -1;
        int actual = rentingDAO.getBookIdFromRenting(1,user);

        Assertions.assertEquals(expectedResult,actual);
    }
}
