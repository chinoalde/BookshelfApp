package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book testBook;

    @BeforeEach
    public void runBefore() {
        testBook = new Book("Book", "Chino","Non-fiction");
    }

    @Test
    public void testConstructor() {
        assertEquals("Book", testBook.getTitle());
        assertEquals("Chino", testBook.getAuthor());
        assertEquals("Non-fiction", testBook.getGenre());
    }

    @Test
    public void testGetReadingStatus() {
        assertEquals("Want to Read", testBook.getReadingStatus());
    }

    @Test
    public void testSetReadingStatus() {
        assertEquals("Want to Read", testBook.getReadingStatus());

        testBook.setReadingStatus(ReadingStatus.READING);
        assertEquals("Reading", testBook.getReadingStatus());
    }

}