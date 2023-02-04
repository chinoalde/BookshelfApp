package model;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }

    //MODIFIES: this
    //E
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        for (Book b: books) {
            if (b.getTitle().equals(title)) {
                books.remove(b);
                break;
            }
        }
    }

    public int bookListSize() {
        return books.size();
    }

    public Book getBook(String title) {
        for (Book b: books) {
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;

    }

    public List<Book> getAllBooks() {
        return books;

    }





}
