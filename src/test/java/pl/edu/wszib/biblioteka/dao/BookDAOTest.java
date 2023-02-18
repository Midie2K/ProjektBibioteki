package pl.edu.wszib.biblioteka.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.biblioteka.model.Book;

public class BookDAOTest {

    @Test
    public void SuccesedFoundBookByTitle(){
        BookDAO bookDAO = BookDAO.getInstance();
        Book test = new Book(1,"Stary człowiek i morze","Ernest Hemingway","11111111",true);

        boolean expectedResult = true;
        boolean actual = bookDAO.findBookByTitle(test);

        Assertions.assertEquals(expectedResult,actual);
    }
    @Test
    public void FailedFoundBookByTitle(){
        BookDAO bookDAO = BookDAO.getInstance();
        Book test = new Book(0,"Język migowy","autor niewidomy","12340987",true);

        boolean expectedResult = false;
        boolean actual = bookDAO.findBookByTitle(test);

        Assertions.assertEquals(expectedResult,actual);
    }
    @Test
    public void SuccesedFoundBookByid(){
        BookDAO bookDAO = BookDAO.getInstance();
        Book expectedResult = new Book(1,"Stary człowiek i morze","Ernest Hemingway","11111111",true);
        Book actual = bookDAO.getBookByid(1);

        boolean ComparedData = false;

        if(expectedResult.getId()==actual.getId() &&
                expectedResult.getTitle().equals(actual.getTitle()) &&
                expectedResult.getAuthor().equals(actual.getAuthor()) &&
                expectedResult.getIsbn().equals(actual.getIsbn())){
            ComparedData = true;
        }

        Assertions.assertTrue(ComparedData);
    }
    @Test
    public void FailedFoundBookByid(){
        BookDAO bookDAO = BookDAO.getInstance();
        Book expectedResult = null;
        Book actual = bookDAO.getBookByid(0);

        Assertions.assertEquals(expectedResult,actual);
    }
    @Test
    public void SuccesedFoundBookByidString(){
        BookDAO bookDAO = BookDAO.getInstance();
        Book expectedResult = new Book(1,"Stary człowiek i morze","Ernest Hemingway","11111111",true);
        Book actual = bookDAO.getBookByid("1");

        boolean ComparedData = false;

        if(expectedResult.getId()==actual.getId() &&
                expectedResult.getTitle().equals(actual.getTitle()) &&
                expectedResult.getAuthor().equals(actual.getAuthor()) &&
                expectedResult.getIsbn().equals(actual.getIsbn())){
            ComparedData = true;
        }

        Assertions.assertTrue(ComparedData);
    }
    @Test
    public void FailedFoundBookByidString(){
        BookDAO bookDAO = BookDAO.getInstance();
        Book expectedResult = null;
        Book actual = bookDAO.getBookByid("0");

        Assertions.assertEquals(expectedResult,actual);
    }
}
