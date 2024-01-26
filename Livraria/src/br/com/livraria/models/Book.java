package models;
import java.util.ArrayList;

public class Book {

    private String title;
    private String isbn;
    private double price;
    private int publisher_id;
    private String PublisherName;

    private ArrayList<BooksAuthors> booksAuthors = new ArrayList<BooksAuthors>();
    private String authorName;

    @Override
    public String toString() {
        String authorInfo = "Autor Desconhecido";
        if (authorName != null) {
            authorInfo = "Autor: " + authorName;
        }

        return "Título: " + title + "\nISBN: " + isbn + "\nPreço: " + price +
                "\nEditora: " + PublisherName + "\n" + authorInfo;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public ArrayList<BooksAuthors> getBooksAuthors(int idAutor) {
        return booksAuthors;
    }

    public void setBooksAuthors(ArrayList<BooksAuthors> booksAuthors) {
        this.booksAuthors = booksAuthors;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public void setPublisherName(String publisherName) {
        PublisherName = publisherName;
    }

}


