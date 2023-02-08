package model;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a book to the list of books
    public void addBook(Book book) {
        books.add(book);
    }

    //REQUIRES: books is not empty
    //MODIFIES: this
    //EFFECTS: removes the book with the matching
    //         title from the list of books
    public void removeBook(String title) {
        for (Book b: books) {
            if (b.getTitle().equals(title)) {
                books.remove(b);
                break;
            }
        }
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





}
