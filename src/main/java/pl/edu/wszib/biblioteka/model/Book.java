package pl.edu.wszib.biblioteka.model;

public class Book {
    int id;
    String title;
    String author;
    String isbn; //8 znakow
    Boolean available;

    public Book(){
        this.id = 0;
        this.available = true;
    }
    public Book(int id, String name, String author, String isbn, boolean available) {
        this.id = id;
        this.title = name;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.id)
                .append("\t | \t")
                .append(this.title)
                .append("\t | \t")
                .append(this.author)
                .append("\t | \t")
                .append(this.isbn)
                .append("\t | \t")
                .append(this.available)
                .toString();
    }
}
