package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book testBook, testBook2;

    @BeforeEach
    public void runBefore() {
        testBook = new Book("Book", "Chino","Non-fiction", 10);
        testBook2 = new Book("1984", "George Orwell", "Fiction", 6);
    }

    @Test
    public void testConstructor() {
        assertEquals("Book", testBook.getTitle());
        assertEquals("Chino", testBook.getAuthor());
        assertEquals("Non-fiction", testBook.getGenre());
    }

    @Test
    public void testGetReadingStatus() {
        assertEquals(ReadingStatus.WANT_TO_READ, testBook.getReadingStatus());
    }


    @Test
    public void testSetReadingStatus() {
        assertEquals(ReadingStatus.WANT_TO_READ, testBook.getReadingStatus());

        testBook.setReadingStatus(ReadingStatus.READING);
        assertEquals(ReadingStatus.READING, testBook.getReadingStatus());
    }

    @Test
    public void testToString() {
        assertEquals("1984 by George Orwell, Genre: Fiction, Rating: 6/10 , Reading Status: WANT_TO_READ",
                testBook2.toString());
    }

    @Test
    public void testEquals() {
        Book target = new Book("Book", "Chino", "Non-fiction", 10);
        assertTrue(testBook.equals(target));

        target = new Book("Boook", "Chino", "Non-fiction", 10);
        assertFalse(testBook.equals(target));

        target = new Book("Book", "Cheeno", "Non-fiction", 10);
        assertFalse(testBook.equals(target));

        target = new Book("Book", "Chino", "Non-fiction", 1);
        assertFalse(testBook.equals(target));

        target = new Book("Book", "Chino", "Non-fiction", 10, ReadingStatus.READ);
        assertFalse(testBook.equals(target));
    }

    @Test
    public void testHashCode() {
        Book b1 = new Book("Book", "Chino","Non-fiction", 10);

        assertTrue(b1.hashCode() == testBook.hashCode());
    }

}