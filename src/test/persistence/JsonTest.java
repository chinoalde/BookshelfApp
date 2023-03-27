package persistence;

import model.Book;
import model.ReadingStatus;

import static org.junit.jupiter.api.Assertions.*;
// this class is modelled from the JsonReaderTest class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkBook(String title, String author, String genre, int rating, ReadingStatus readingStatus, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
        assertEquals(rating, book.getRating());
        assertEquals(readingStatus.getStatus(), book.getReadingStatus().getStatus());
    }
}
