package model;

public class Book {
    private final String title;
    private final String author;
    private final String genre;
    private ReadingStatus status;



    public Book(String bookTitle, String bookAuthor, String genre) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.genre = genre;
        this.status = ReadingStatus.READING;
    }

    //REQUIRES: doneReading is lower case
    //MODIFIES: this
    //EFFECTS: if doneReading == "yes", sets isRead to true. else, leaves isRead to false
    //setter maybe?


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }



}
