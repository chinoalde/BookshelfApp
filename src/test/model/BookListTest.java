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
        b1 = new Book("Percy Jackson", "Chino", "Fantasy", 10, ReadingStatus.READING);
        b2 = new Book("1984", "George Orwell", "Fiction", 8, ReadingStatus.READ);
        b3 = new Book("Think Like a Monk", "Jay Shetty", "Self-help", 9,
                ReadingStatus.WANT_TO_READ);
        b4 = new Book("A Little Life", "Chino", "Fiction", 10,
                ReadingStatus.WANT_TO_READ);
    }

    @Test
    public void testAddBooks() {
        assertEquals(0, testBooks.bookListSize());
        testBooks.addBook(b1, "add");
        assertEquals(1, testBooks.bookListSize());
        testBooks.addBook(b2, "add");
        testBooks.addBook(b3, "add");
        assertEquals(3, testBooks.bookListSize());
        assertEquals("Percy Jackson", testBooks.getAllBooks().get(0).getTitle());


    }

    @Test
    public void testRemoveBooks() {
        assertEquals(0, testBooks.bookListSize());

        //list is empty
        assertEquals(0, testBooks.bookListSize());
        testBooks.removeBook(b1);

        testBooks.addBook(b1, "add");
        testBooks.addBook(b2, "add");
        testBooks.addBook(b3, "add");

        //book is in the list and can be removed
        assertEquals(3, testBooks.bookListSize());
        testBooks.removeBook(b1);
        assertEquals(2, testBooks.bookListSize());
        assertEquals(b2.getTitle(), testBooks.getAllBooks().get(0).getTitle());

        testBooks.addBook(b1, "add");

        //book is not in the list
        testBooks.removeBook(b4);
        assertEquals(3, testBooks.bookListSize());
    }

    @Test
    public void testGetBook() {
        //list is empty
        Book testBook1;
        testBook1 = testBooks.getBook("Percy Jackson");
        assertEquals(null, testBook1);

        testBooks.addBook(b1, "add");
        testBooks.addBook(b2, "add");
        testBooks.addBook(b3, "add");

        //book is in the book set
        Book testBook = testBooks.getBook("Percy Jackson");
        assertEquals(b1, testBook);

        //book is not in the book set
        assertEquals(null, testBooks.getBook("not here"));
    }

    @Test
    public void testGetBooksFromGenre() {
        testBooks.addBook(b1, "add");
        testBooks.addBook(b2, "add");
        testBooks.addBook(b3, "add");
        testBooks.addBook(b4, "add");

        List<Book> allBooksFiction = testBooks.getBooksFromGenre("Fiction");
        assertEquals(2, allBooksFiction.size());
        assertEquals(b2, allBooksFiction.get(0));
        assertEquals(b4, allBooksFiction.get(1));
    }

    @Test
    public void testGetBooksFromAuthor() {
        testBooks.addBook(b1, "add");
        testBooks.addBook(b2, "add");
        testBooks.addBook(b3, "add");
        testBooks.addBook(b4, "add");

        List<Book> allBooksByChino = testBooks.getBooksByAuthor("Chino");
        assertEquals(2, allBooksByChino.size());
        assertEquals(b1, allBooksByChino.get(0));
        assertEquals(b4, allBooksByChino.get(1));
    }

    @Test
    public void testHasBook() {
        //list is empty
        assertFalse(testBooks.hasBook("Percy Jackson"));

        //list has the book
        testBooks.addBook(b1, "add");
        assertTrue(testBooks.hasBook("Percy Jackson"));

        //list does not have the book
        assertFalse(testBooks.hasBook("1984"));
    }

    @Test
    public void testGetBooksByReadingStatus() {
        testBooks.addBook(b1, "add");
        testBooks.addBook(b2, "add");
        testBooks.addBook(b3, "add");
        testBooks.addBook(b4, "add");

        List<Book> booksWantToRead = testBooks.getBooksByReadingStatus(ReadingStatus.WANT_TO_READ);
        assertEquals(2, booksWantToRead.size());
        assertEquals(b3, booksWantToRead.get(0));
        assertEquals(b4, booksWantToRead.get(1));


    }


}
