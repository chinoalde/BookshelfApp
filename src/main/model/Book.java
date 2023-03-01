package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// represents a book with a title, author, genre, and reading status
public class Book implements Writable {
    private final String title;
    private final String author;
    private final String genre;
    private int rating;
    private ReadingStatus status;


    //REQUIRES: rating is between 1 and 10
    public Book(String bookTitle, String bookAuthor, String genre, int rating) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.genre = genre;
        this.rating = rating;
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

    //EFFECTS: returns rating
    public int getRating() {
        return rating;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("rating", rating);

        return json;
    }
}
