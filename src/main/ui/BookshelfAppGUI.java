package ui;

import model.Book;
import model.BookList;
import model.ReadingStatus;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BookshelfApp GUI
// class references code from https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class BookshelfAppGUI {
    public static final int WIDTH = 440;
    public static final int HEIGHT = 800;

    private static final String DIRECTORY = "./data/booklist.json";
    private BookList bookList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<String> books;

    private Frame frame;
    private TitlePanel titlePanel;
    private CardLayoutPanel cardLayoutPanel;
    private StartUpPanel startUpPanel;
    private HomePagePanel homePagePanel;
    private ShowBooksPanel showBooksPanel;
    private ShowBooksFromGenrePanel showBooksFromGenre;
    private ShowBooksByReadingStatusPanel showBooksByReadingStatusPanel;
    private AddBookPanel addBookPanel;
    private DeleteBookPanel deleteBookPanel;

    private CardLayout cards;

    //EFFECTS; runs the BookshelfAppGUI application
    public BookshelfAppGUI() throws FileNotFoundException {
        runApp();
    }



    //EFFECTS: runs the BookshelfApp
    private void runApp() {
        init();

        frame.add(titlePanel);
        cardLayoutPanel.setLayout(cards);
        cardLayoutPanel.add(startUpPanel, "start up page");

        cards.show(cardLayoutPanel, "start up page");
        frame.add(cardLayoutPanel);

        frame.setVisible(true);
    }

    //represents the AddBookPanel
    private class AddBookPanel extends AddBookPanelNoSubmit {

        public AddBookPanel(int width, int height, BookList bookList, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, bookList, cards, cardLayoutPanel);
            this.submit = new JButton("add book");
            this.submit = new JButton("add anime");
            this.submit.setBounds(160, 600, 100, 50);
            this.submit.setFont(new Font("Monospaced", Font.PLAIN, 12));

            this.submit.addActionListener(e -> {
                String titleValue = titleField.getText();
                String authorValue = authorField.getText();
                String genreValue = (String) selectGenre.getSelectedItem();
                Integer ratingValue = (Integer) selectRating.getSelectedItem();
                ReadingStatus readingStatusValue = (ReadingStatus) selectReadingStatus.getSelectedItem();

                Book newBook = new Book(titleValue, authorValue, genreValue, ratingValue, readingStatusValue);
                bookList.addBook(newBook);
                showBooksPanel.refresh(bookList.getAllBooks());
                showBooksFromGenre.refresh(bookList.getAllBooks());
                showBooksByReadingStatusPanel.refresh(bookList.getAllBooks());
                deleteBookPanel.refresh(bookList.getAllBooks());
                refreshPanel();
            });
            this.add(submit);
        }

        //MODIFIES: titleField, authorField, selectGenre, selectRating, selectReadingStatus
        //EFFECTS: resets values of all the inputs
        private void refreshPanel() {
            List<JFormattedTextField> fieldList = new ArrayList<>(Arrays.asList(
                    titleField, authorField));

            List<JComboBox> boxList = new ArrayList<>(Arrays.asList(
                    selectGenre, selectRating, selectReadingStatus));

            for (JFormattedTextField field : fieldList) {
                field.setText("");
            }

            for (JComboBox box : boxList) {
                box.setSelectedIndex(-1);
            }

        }
    }

    //represents the DeleteBookPanel
    private class DeleteBookPanel extends ShowBooksPanel {
        private JButton deleteButton;

        public DeleteBookPanel(int width, int height, BookList bookList, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, bookList, cards, cardLayoutPanel);
            this.deleteButton = new JButton("delete");
            this.deleteButton.setBounds(160, 600, 100, 50);
            this.deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
            this.deleteButton.addActionListener(e -> {
                bookList.removeBook(jlist.getSelectedValue());

                this.refresh(bookList.getAllBooks());
                showBooksPanel.refresh(bookList.getAllBooks());
                showBooksFromGenre.refresh(bookList.getAllBooks());
                showBooksByReadingStatusPanel.refresh(bookList.getAllBooks());
            });
            this.add(deleteButton);
        }
    }

    //Represents a StartUpPanel
    private class StartUpPanel extends StartUpPanelNoStartButton {
        private JButton start;
        private ImageIcon saveIcon;

        public StartUpPanel(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, cards, cardLayoutPanel);

            this.start = new JButton("start");
            this.start.setLayout(new GridBagLayout());
            this.start.setBounds(160, 550, 100, 50);
            this.start.setFont(new Font("Monospaced", Font.PLAIN, 14));


            //image from: https://www.pngfind.com/download/iiiRibm_png-file-save-icon-vector-png-transparent-png/
            this.saveIcon = new ImageIcon(new ImageIcon("./data/Save.png").getImage().getScaledInstance(
                    30, 30, Image.SCALE_DEFAULT));


            this.start.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(frame, "do you want to load your data?",
                        "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, saveIcon);
                if (option == 0) {
                    loadFile();
                }
                initOtherPanels();
                cards.show(cardLayoutPanel, "home page");
            });
            this.add(start);

        }
    }

    //Represents a HomePagePanel
    private class HomePagePanel extends HomePagePanelNoSaveQuit {
        private JButton save;
        private JButton quit;
        private ImageIcon saveIcon;

        //Constructs a HomePagePanel
        public HomePagePanel(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, cards, cardLayoutPanel);

            this.save = new JButton("save file");
            this.quit = new JButton("quit");
            this.saveIcon = new ImageIcon(new ImageIcon("./data/save icon.png").getImage().getScaledInstance(
                    30, 30, Image.SCALE_DEFAULT));

            setUp();
            addActions();

            this.add(this.save);
            this.add(this.quit);
        }

        // MODIFIES: save, quit
        // EFFECTS: adds Action Listeners and their corresponding actions
        private void addActions() {
            this.save.addActionListener(e -> {
                saveFile();
            });
            this.quit.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(frame, "do you want to overrride your saved data?",
                        "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, this.saveIcon);
                if (option == 0) {
                    saveFile();
                }
                frame.dispose();
            });
        }

        // MODIFIES: save, quit
        // EFFECTS: sets the bounds and fonts of the buttons
        private void setUp() {
            this.save.setBounds(40, 650, 100, 50);
            this.quit.setBounds(280, 650, 100, 50);

            this.save.setFont(new Font("Monospaced", Font.PLAIN, 12));
            this.quit.setFont(new Font("Monospaced", Font.PLAIN, 12));
        }

    }


    //EFFECTS: saves books to file
    private void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("There was an error saving your file.");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads books from file
    private void loadFile() {
        try {
            this.bookList = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes cards, frame, titlePanel, cardLayoutPanel, startUpPanel
    private void init() {
        bookList = new BookList();
        jsonWriter = new JsonWriter(DIRECTORY);
        jsonReader = new JsonReader(DIRECTORY);

        cards = new CardLayout();
        frame = new Frame(WIDTH, HEIGHT);
        titlePanel = new TitlePanel(WIDTH, HEIGHT);
        cardLayoutPanel = new CardLayoutPanel(WIDTH, HEIGHT);
        startUpPanel = new StartUpPanel(WIDTH, HEIGHT, cards, cardLayoutPanel);
    }

    //MODIFIES: this
    //EFFECTS: initializes all the other panels and adds them to cardLayoutPanel
    private void initOtherPanels() {
        homePagePanel = new HomePagePanel(WIDTH, HEIGHT, cards, cardLayoutPanel);
        showBooksPanel = new ShowBooksPanel(WIDTH, HEIGHT, bookList, cards, cardLayoutPanel);
        showBooksFromGenre = new ShowBooksFromGenrePanel(WIDTH, HEIGHT,
                bookList, cards, cardLayoutPanel);
        showBooksByReadingStatusPanel = new ShowBooksByReadingStatusPanel(WIDTH, HEIGHT,
                bookList, cards, cardLayoutPanel);
        addBookPanel = new AddBookPanel(WIDTH, HEIGHT, bookList, cards, cardLayoutPanel);
        deleteBookPanel = new DeleteBookPanel(WIDTH, HEIGHT, bookList, cards, cardLayoutPanel);

        cardLayoutPanel.add(homePagePanel, "home page");
        cardLayoutPanel.add(showBooksPanel, "all books");
        cardLayoutPanel.add(showBooksFromGenre, "books from genre", JLabel.CENTER);
        cardLayoutPanel.add(showBooksByReadingStatusPanel, "books with reading status");
        cardLayoutPanel.add(addBookPanel, "add book");
        cardLayoutPanel.add(deleteBookPanel, "delete book");
    }

}
