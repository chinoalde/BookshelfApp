package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a list of books
public class BookList implements Writable {
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a book to the list of books
    public void addBook(Book book, String option) {
        books.add(book);
        if (option.equals("load")) {
            EventLog.getInstance().logEvent(new Event("Loaded book  " + book.getTitle()
                    +
                    " to list."));
        } else if (option.equals("add")) {
            EventLog.getInstance().logEvent(new Event("Added book  " + book.getTitle()
                    +
                    " to list."));
        }
    }

    //REQUIRES: books is not empty
    //MODIFIES: this
    //EFFECTS: removes the book with the matching
    //         title from the list of books
    public void removeBook(Book target) {
        books.remove(target);
        EventLog.getInstance().logEvent(new Event("book anime " + target.getTitle()
                +
                " from list."));

    }
//    public void removeBook(String title) {
//        for (Book b: books) {
//            if (b.getTitle().equals(title)) {
//                books.remove(b);
//            }
//        }
//    }

    //REQUIRES: books is not empty
    //EFFECTS: gets books with the matching genre from the list of books and returns it
    public List<Book> getBooksFromGenre(String genre) {
        List<Book> booksFromGenre = new ArrayList<>();

        for (Book b: books) {
            if (b.getGenre().equals(genre)) {
                booksFromGenre.add(b);
            }
        }
        return booksFromGenre;
    }

    //REQUIRES: books is not empty
    //EFFECTS: gets books written by matching author from list of books and returns it
    public List<Book> getBooksByAuthor(String author) {
        List<Book> booksFromAuthor = new ArrayList<>();

        for (Book b: books) {
            if (b.getAuthor().equals(author)) {
                booksFromAuthor.add(b);
            }
        }
        return booksFromAuthor;

    }

    //REQUIRES: books is not empty
    //EFFECTS: gets books written by matching reading status from list of books and returns it
    public List<Book> getBooksByReadingStatus(ReadingStatus status) {
        List<Book> booksByReadingStatus = new ArrayList<>();

        for (Book b: books) {
            if (b.getReadingStatus().equals(status)) {
                booksByReadingStatus.add(b);
            }
        }
        return booksByReadingStatus;

    }



    //EFFECTS: returns the number of books in the list
    public int bookListSize() {
        return books.size();
    }

    //REQUIRES: books is not empty
    //EFFECTS: gets the book with the matching title from the list of books
    public Book getBook(String title) {
        for (Book b: books) {
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }

    //REQUIRES: books is not empty
    //EFFECTS: returns all books in the list
    public List<Book> getAllBooks() {
        return books;
    }


    //REQUIRES: books is not empty
    //EFFECTS: returns true if book is in given list, else false
    public boolean hasBook(String title) {
        for (Book b: books) {
            if (b.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        return json;
    }

    //EFFECTS: returns books in booklist as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Book b : books) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }











}
