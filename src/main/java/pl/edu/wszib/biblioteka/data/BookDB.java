package pl.edu.wszib.biblioteka.data;

import pl.edu.wszib.biblioteka.model.Book;
import pl.edu.wszib.biblioteka.model.Role;
import pl.edu.wszib.biblioteka.model.User;

import java.util.ArrayList;
import java.util.List;

public class BookDB {
    private List<Book> books = new ArrayList<>();
    private static final BookDB instance = new BookDB();

    private BookDB(){
        books.add(new Book("Stary czlowiek i morze","Ernest Hemingway","11111111"));
        books.add(new Book("Zew Cthulhu","H.P. Lovecraft","22222222"));
        books.add(new Book("Metro 2033","Dmitry Glukhovsky","20332033"));
        books.add(new Book("Czarnobyl. Spowiedz reportera","Igor Kostin","26041986"));
        books.add(new Book("Opowiesci z Narnii","C.S. Lewis","97883727"));
        books.add(new Book("Listy z Auschwitz","Janusz Pogonowski","97883770"));
        books.add(new Book("Chlopcy z Placu Broni","Ferenc Molnar","97883101"));
        books.add(new Book("Pan Tadeusz","Adam Mickiewicz","97883662"));
        books.add(new Book("Kamienie na szaniec","Aleksander Kaminski","97883101"));
        books.add(new Book("Wings of Fire ","Tui T. Sutherland","96847917"));
    }


    public static BookDB getInstance(){
        return instance;
    }
    public void getBooks() {
        System.out.println(books.stream().toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));;
    }
    public void getBooksByName(String name) {
        System.out.println(books.stream()
                .filter(book -> book.getName().toLowerCase().contains(name.toLowerCase()))
                .toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));;
    }
    public void getBooksByAuthor(String author) {
        System.out.println(books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));;
    }

    public boolean addBook(String name, String author, String isbn){
        Book book = books.stream()
                .filter(book1 -> name.equals(book1.getName()))
                .findAny()
                .orElse(null);

        if (book == null){
            books.add(new Book(name, author, isbn));
            return true;
        }
        else{
            return false;
        }
    }

}
