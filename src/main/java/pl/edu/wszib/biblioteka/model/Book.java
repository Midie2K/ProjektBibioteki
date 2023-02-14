package pl.edu.wszib.biblioteka.model;

public class Book {
    String name;
    String author;
    String isbn; //8 znakow
    Boolean available;

    public Book(String name, String author, String isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .append(this.name)
                .append("\t | \t")
                .append(this.author)
                .append("\t | \t")
                .append(this.isbn)
                .toString();
    }
}
