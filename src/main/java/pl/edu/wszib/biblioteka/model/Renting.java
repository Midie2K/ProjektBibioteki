package pl.edu.wszib.biblioteka.model;

import java.sql.Date;
import java.time.LocalDate;


public class Renting {
    private int id;
    private int user_id;
    private int book_id;
    private Date dateOfRent;
    private Date endOfRent;
    private Date dateOfReturn;
    private User user;
    private Book book;



    public Renting(int id, int user_id, int book_id, Date dateOfRent, Date endOfRent, User user, Book book) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.dateOfRent = dateOfRent;
        this.endOfRent = endOfRent;
        this.user = user;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Date getDateOfRent() {
        return dateOfRent;
    }

    public void setDateOfRent(Date dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    public Date getEndOfRent() {
        return endOfRent;
    }

    public void setEndOfRent(Date endOfRent) {
        this.endOfRent = endOfRent;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.id)
                .append("\t | \t")
                .append(this.book.getTitle())
                .append("\t | \t")
                .append(this.book.getAuthor())
                .append("\t | \t")
                .append(this.book.getIsbn())
                .append("\t | \t")
                .append(this.user.getName())
                .append("\t | \t")
                .append(this.user.getSurname())
                .append("\t | \t")
                .append(this.dateOfRent)
                .append("\t | \t")
                .append(this.endOfRent)
                .toString();
    }
}
