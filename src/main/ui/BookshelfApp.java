package ui;


import model.Book;
import model.BookList;
import model.ReadingStatus;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Bookshelf application
//references code from https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class BookshelfApp {
    private BookList bookList;
    private Scanner input;

    private static final String JSON_STORE = "./data/booklist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the bookshelf application
    public BookshelfApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        bookList = new BookList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runBookshelf();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runBookshelf() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        init();
        displayWelcome();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you next time!");
    }

    //MODIFIES: this
    //EFFECTS: initializes books on bookshelf
    private void init() {
        Book b1 = new Book("Think Like a Monk", "Jay Shetty", "Self-help", 10);

        bookList = new BookList();
        bookList.addBook(b1);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n");
        System.out.println("\tPlease select one of the options below:");
        System.out.println("\t1 -> view all books");
        System.out.println("\t2 -> change reading status of a book");
        System.out.println("\ta -> add a new book");
        System.out.println("\tr -> remove an existing book");
        System.out.println("\ts -> save books to file");
        System.out.println("\tl -> load books from file");
        System.out.println("\tq -> quit");
        System.out.println("\n");

    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            viewAllBooks();
        } else if (command.equals("2")) {
            changeReadingStatus();
        } else if (command.equals("a")) {
            addBookToBookshelf();
        } else if (command.equals("r")) {
            removeBookFromBookshelf();
        } else if (command.equals("s")) {
            saveBookShelf();
        } else if (command.equals("l")) {
            loadBookShelf();

        } else {
            System.out.println("Selection not valid... Please try again!");
            String newCommand = input.next().toLowerCase();
            processCommand(newCommand);

        }
    }

    private void saveBookShelf() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookList);
            jsonWriter.close();
            System.out.println("Saved books to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadBookShelf() {
        try {
            bookList = jsonReader.read();
            System.out.println("Loaded books from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    //EFFECTS: prints welcome message
    private void displayWelcome() {
        System.out.println("\n");
        System.out.println("\tWelcome to the Bookshelf App!");
        System.out.println("\t-------------------------------");
    }

    //EFFECTS: prints all book entries
    private void viewAllBooks() {
        List<Book> books = bookList.getAllBooks();
        displayBooks(books);
    }

    //EFFECTS: displays all book entries
    private void displayBooks(List<Book> books) {
        if (books.size() == 0) {
            System.out.println("There are no books. Add some!");
        } else {
            System.out.println("Here are all of your books:");
            for (Book b : books) {
                System.out.println(b.getTitle());
            }
        }
    }

    //TODO
    private void viewBooksFromGenre() {
    }

    //TODO
    private void viewBooksFromAuthor() {
    }

    //REQUIRES: action is one of the given commands and the book is in the book list
    //MODIFIES: this
    //EFFECTS: changes reading status of a book
    private void changeReadingStatus() {
        System.out.println("Please enter the title of the book you want to change the status for");

        String title = input.next();
        Book book = bookList.getBook(title);
        if (bookList.hasBook(title)) {
            displayingReadingStatus();
            String command = input.next();
            setBookStatus(book, command);
            printReadingStatus(book);
        } else {
            System.out.println("Can't change the status of a book that isn't there!");
        }

    }


    //MODIFIES: this
    //EFFECTS: helper method to change reading status of a book
    // can change the name of this method - TODO
    private void setBookStatus(Book book, String command) {
        if (command.equals("1")) {
            book.setReadingStatus(ReadingStatus.WANT_TO_READ);
        } else if (command.equals("2")) {
            book.setReadingStatus(ReadingStatus.READING);
        } else if (command.equals("3")) {
            book.setReadingStatus(ReadingStatus.READ);
        }
    }

    //EFFECTS: displays reading status options of a book
    private void displayingReadingStatus() {
        System.out.println("1 -> Want to Read");
        System.out.println("2 -> Reading");
        System.out.println("3 -> Read");
    }


    //MODIFIES: this
    //EFFECTS: adds book to bookshelf
    private void addBookToBookshelf() {

        System.out.println("Enter the title of the book: ");
        String title = input.next();

        System.out.println("Enter the author of the book: ");
        String author = input.next();

        System.out.println("Enter the genre of the book: ");
        String genre = input.next();

        System.out.println("Enter the rating of the book: ");
        int rating = input.nextInt();

        Book newBook = new Book(title, author, genre, rating);

        bookList.addBook(newBook);

        System.out.println("Voila! " + newBook.getTitle() + " has been added to the bookshelf!");
    }

    //REQUIRES: book is on the shelf
    //MODIFIES: this
    //EFFECTS: removes book from bookshelf
    private void removeBookFromBookshelf() {
        System.out.println("\tEnter the title of the book you'd like to remove: ");

        String title = input.next();

        if (bookList.hasBook(title)) {
            bookList.removeBook(title);
            System.out.println(title + " has been removed!");
        } else {
            System.out.println("Can't remove a book that isn't on the shelf! Please try again");
        }

    }

    //EFFECTS: prints out reading status of selected book
    private void printReadingStatus(Book selected) {
        System.out.println("\nThe reading status for " + selected.getTitle()
                + " is now: \n" + selected.getReadingStatus());
    }


}






