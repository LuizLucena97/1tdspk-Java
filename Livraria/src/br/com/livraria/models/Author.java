package models;

import java.util.ArrayList;
import java.util.List;

public class Author {

    private int author_id;
    private String name;
    private String fName;
    private ArrayList<BooksAuthors> booksAuthors = new ArrayList<BooksAuthors>();

    @Override
    public String toString() {
        return "Author ID: " + author_id + "\n" +
                "Name: " + name + "\n" +
                "FName: " + fName;
    }



    public static Author findAuthorByName(String name, List<Author> authors) {
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(name)) {
                return author;
            }
        }
        return null; 
    }
    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_Id(int id) {
        this.author_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public ArrayList<BooksAuthors> getBooksAuthors() {
        return booksAuthors;
    }

    public void setBooksAuthors(ArrayList<BooksAuthors> booksAuthors) {
        this.booksAuthors = booksAuthors;
    }

}

