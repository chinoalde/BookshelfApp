package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// represents a reader that reads a bookshelf from JSON data stored in file
// references code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads book list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookList(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses book list from JSON object and returns it
    private BookList parseBookList(JSONObject jsonObject) {
//        String title = jsonObject.getString("title");
        BookList bl = new BookList();
        addBooks(bl, jsonObject);
        return bl;
    }

    //MODIFIES: bl
    //EFFECTS: parses books from JSON object and adds them to book list
    private void addBooks(BookList bl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(bl, nextBook);
        }
    }


    //MODIFIES: bl
    //EFFECTS: parses book from JSON objects and adds it to book list
    private void addBook(BookList bl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        String genre = jsonObject.getString("genre");
        int rating = jsonObject.getInt("rating");


        Book b = new Book(title, author, genre, rating);
        bl.addBook(b);
    }

}
