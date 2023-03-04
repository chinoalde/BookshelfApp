package persistence;

import model.Book;
import model.BookList;
import model.ReadingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// this class is modelled from the persistence.JsonReaderTest class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest{

    private BookList books;

    @BeforeEach
    public void runBefore() {
        books = new BookList();
    }

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            books = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyBookList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookList.json");
        try {
            BookList books = reader.read();
            assertEquals(0, books.getAllBooks().size());
        } catch (IOException e) {
            fail("couldn't read file");
        }
    }

    @Test
    public void testReaderNonEmptyBookList() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyBookList.json.");
        try {
            BookList books = reader.read();
            List<Book> bl = books.getAllBooks();
            assertEquals(2, bl.size());
            checkBook("Think Like a Monk", "Jay Shetty", "Self Help", 10,
                    ReadingStatus.WANT_TO_READ, bl.get(0));
        } catch (IOException e) {
            fail("couldn't read file");
        }

    }

}
