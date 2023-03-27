package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;


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

    public Book(String bookTitle, String bookAuthor, String genre, int rating, ReadingStatus status) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.genre = genre;
        this.rating = rating;
        this.status = status;
    }


    //MODIFIES: this
    //EFFECTS: updates reading status of the book
    public void setReadingStatus(ReadingStatus status) {
        this.status = status;
    }

    //EFFECTS: returns reading status
    public ReadingStatus getReadingStatus() {
        return status;
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
        json.put("reading status", status);

        return json;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;
        return rating == book.rating
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(genre, book.genre)
                && status == book.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, rating, status);
    }

    @Override
    public String toString() {
        return title + " by " + author;
//                + " - genre: " + genre + " - rating: " + rating
//                + " - status: " + status;

    }
}
