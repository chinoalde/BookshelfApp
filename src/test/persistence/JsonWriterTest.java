package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The class is modelled from the JsonWriterTest class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest{
    private BookList books;

    @BeforeEach
    public void runBefore() {
        books = new BookList();
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("exception expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testWriterEmptyBookList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookList.json");
            writer.open();
            writer.write(books);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookList.json");
            books = reader.read();
            List<Book> bookList = books.getAllBooks();
            assertEquals(0, bookList.size());
        } catch (IOException e) {
            fail("an exception should have been thrown");
        }
    }

    @Test
    public void testWriterNonEmptyBookList() {
        try {
            books.addBook(new Book("A Little Life", "Hanya Yanagihara", "Fiction", 10));
            books.addBook(new Book("1984", "George Orwell", "Fantasy", 1));
            books.addBook(new Book("Lord of the Flies", "William Golding", "Fantasy", 7));

            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyBookList.json");
            writer.open();
            writer.write(books);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyBookList.json");
            books = reader.read();
            List<Book> bookList = books.getAllBooks();
            assertEquals(3, bookList.size());
            assertEquals("A Little Life", bookList.get(0).getTitle());
            assertEquals("Fiction", bookList.get(0).getGenre());
            assertEquals(1, bookList.get(1).getRating());
            assertEquals("William Golding", bookList.get(2).getAuthor());
        } catch (IOException e) {
            fail("exception should not have been thrown");
        }
    }
}
