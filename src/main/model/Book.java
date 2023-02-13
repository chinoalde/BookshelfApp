package model;

// represents a book with a title, author, genre, and reading status
public class Book {
    private final String title;
    private final String author;
    private final String genre;
    private ReadingStatus status;


    public Book(String bookTitle, String bookAuthor, String genre) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.genre = genre;
        this.status = ReadingStatus.WANT_TO_READ;
    }

    //MODIFIES: this
    //EFFECTS: updates reading status of the book
    public void setReadingStatus(ReadingStatus status) {
        this.status = status;
    }

    //EFFECTS: returns reading status
    public String getReadingStatus() {
        return status.getStatus();
    }

    //EFFECTS: returns title
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns author
    public String getAuthor() {
        return author;
    }

    //EFFECTS: returns genre
    public String getGenre() {
        return genre;
    }



}
