package models;

public class BooksAuthors {

        private Author author;
        private Book book;
        private int seq_no;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getSeq_No() {
        return seq_no;
    }

    public void setSeq_No(int seq_No) {
        seq_no = seq_No;
    }
}
