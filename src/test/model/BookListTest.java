package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookListTest {
    private BookList testBooks;
    private Book b1, b2, b3;




    @BeforeEach
    public void runBefore() {
        testBooks = new BookList();
        b1 = new Book("Percy Jackson", "Rick Riordan", "Fantasy");
        b2 = new Book("1984", "George Orwell", "Fiction");
        b3 = new Book("Think Like a Monk", "Jay Shetty", "Self-help");
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
        testBooks.addBook(b1);
        testBooks.addBook(b2);
        testBooks.addBook(b3);

        assertEquals(3, testBooks.bookListSize());
        testBooks.removeBook("Percy Jackson");
        assertEquals(2, testBooks.bookListSize());
        assertEquals(b2.getTitle(), testBooks.getAllBooks().get(0).getTitle());
    }
}
