package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookListTest {
    private BookList testBooks;
    private Book b1, b2, b3, b4;
    @BeforeEach
    public void runBefore() {
        testBooks = new BookList();
        b1 = new Book("Percy Jackson", "Chino", "Fantasy", 10);
        b2 = new Book("1984", "George Orwell", "Fiction", 8);
        b3 = new Book("Think Like a Monk", "Jay Shetty", "Self-help", 9);
        b4 = new Book("A Little Life", "Chino", "Fiction", 10);
    }

    @Test
    public void testAddBooks() {
        assertEquals(0, testBooks.bookListSize());
        testBooks.addBook(b1);
        assertEquals(1, testBooks.bookListSize());
        testBooks.addBook(b2);
        testBooks.addBook(b3);
        assertEquals(3, testBooks.bookListSize());
    }

    @Test
    public void testRemoveBooks() {
        assertEquals(0, testBooks.bookListSize());

        //list is empty
        assertEquals(0, testBooks.bookListSize());
        testBooks.removeBook(b1.getTitle());

        testBooks.addBook(b1);
        testBooks.addBook(b2);
        testBooks.addBook(b3);

        //book is in the list and can be removed
        assertEquals(3, testBooks.bookListSize());
        testBooks.removeBook("Percy Jackson");
        assertEquals(2, testBooks.bookListSize());
        assertEquals(b2.getTitle(), testBooks.getAllBooks().get(0).getTitle());

        testBooks.addBook(b1);

        //book is not in the list
        testBooks.removeBook("book is not here");
        assertEquals(3, testBooks.bookListSize());
    }

    @Test
    public void testGetBook() {
        //list is empty
        Book testBook1;
        testBook1 = testBooks.getBook("Percy Jackson");
        assertEquals(null, testBook1);

        testBooks.addBook(b1);
        testBooks.addBook(b2);
        testBooks.addBook(b3);

        //book is in the book set
        Book testBook = testBooks.getBook("Percy Jackson");
        assertEquals(b1, testBook);

        //book is not in the book set
        assertEquals(null, testBooks.getBook("not here"));
    }

    @Test
    public void testGetBooksFromGenre() {
        testBooks.addBook(b1);
        testBooks.addBook(b2);
        testBooks.addBook(b3);
        testBooks.addBook(b4);

        List<Book> allBooksFiction = testBooks.getBooksFromGenre("Fiction");
        assertEquals(2, allBooksFiction.size());
        assertEquals(b2, allBooksFiction.get(0));
        assertEquals(b4, allBooksFiction.get(1));
    }

    @Test
    public void testGetBooksFromAuthor() {
        testBooks.addBook(b1);
        testBooks.addBook(b2);
        testBooks.addBook(b3);
        testBooks.addBook(b4);

        List<Book> allBooksByChino= testBooks.getBooksByAuthor("Chino");
        assertEquals(2, allBooksByChino.size());
        assertEquals(b1, allBooksByChino.get(0));
        assertEquals(b4, allBooksByChino.get(1));
    }

    @Test
    public void testHasBook() {
        //list is empty
        assertFalse(testBooks.hasBook("Percy Jackson"));

        //list has the book
        testBooks.addBook(b1);
        assertTrue(testBooks.hasBook("Percy Jackson"));

        //list does not have the book
        assertFalse(testBooks.hasBook("1984"));
    }

//    @Test
//    public void testHasBookButNoBook() {
//        //expecting numBooks to be 1, and no exceptions thrown
//        BookList bookList = new BookList();
//        bookList.addBook(b2);
//
//        try {
//            bookList.hasBook(b2.getTitle());
//        } catch (NoBookOnShelfException e) {
//            fail("Got NoBookOnShelfException when shouldn't have");
//        }
//
//    }
//
}
