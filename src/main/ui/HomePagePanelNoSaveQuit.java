package ui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

// represents a HomePagePanel with no save or quit button
public class HomePagePanelNoSaveQuit extends JPanel {
    private int width;
    private int height;
    private JButton allBooks;
    private JButton allBooksFromGenre;
    private JButton allBooksFromReadingStatus;
    private JButton addBook;
    private JButton deleteBook;
    private CardLayout cards;
    private JPanel cardLayoutPanel;

    public HomePagePanelNoSaveQuit(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
        this.width = width;
        this.height = height;
        this.cards = cards;
        this.cardLayoutPanel = cardLayoutPanel;

        this.setBackground(Color.white);
        this.setBounds((int) (this.width * 0.04), (int) (this.height * 0.05),
                (int) (this.width * 0.95), (int) (this.height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        addItems();
    }

    // MODIFIES: this
    // EFFECTS: adds the welcome text and menu buttons to the panel
    private void addItems() {
        addWelcomeText();
        addMenuButtons();
    }

    // MODIFIES: this
    // EFFECTS: sets up the welcomeText label and adds it to the panel
    // from https://bit.ly/3DfykyB
    private void addWelcomeText() {
        JTextPane welcomeText = new JTextPane();
        welcomeText.setText("Welcome to the BookshelfApp, please select one of the following options");
        welcomeText.setFont(new Font("Monospaced", Font.PLAIN, 14));
        welcomeText.setBounds((int) (width * 0.08), (int) (height * 0.1), (int) (width * 0.8), (int) (height * 0.05));
        welcomeText.setEditable(false);

        StyledDocument doc = welcomeText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.add(welcomeText);
    }

    // MODIFIES: this
    // EFFECTS: sets up the menu buttons and adds them to the panel
    private void addMenuButtons() {
        allBooks = new JButton("view all books");
        allBooksFromGenre = new JButton("view all books from a genre");
        allBooksFromReadingStatus = new JButton("view all books with reading status");
        addBook = new JButton("add new book");
        deleteBook = new JButton("delete existing book");

        menuButtonsSetUp();

        allBooks.addActionListener(e -> cards.show(cardLayoutPanel, "all books"));
        allBooksFromGenre.addActionListener(e -> cards.show(cardLayoutPanel, "books from genre"));
        allBooksFromReadingStatus.addActionListener(e -> cards.show(cardLayoutPanel, "books with reading status"));
        addBook.addActionListener(e -> cards.show(cardLayoutPanel, "add book"));
        deleteBook.addActionListener(e -> cards.show(cardLayoutPanel, "delete book"));

        this.add(allBooks);
        this.add(allBooksFromGenre);
        this.add(allBooksFromReadingStatus);
        this.add(addBook);
        this.add(deleteBook);
    }

    // MODIFIES: allBooks, allBooksFromGenre, allBooksFromReadingStatus, addBook, deleteBook
    // EFFECTS: sets the bounds and fonts for the buttons
    private void menuButtonsSetUp() {
        allBooks.setBounds(80, 160, 250, 50);
        allBooksFromGenre.setBounds(80, 250, 250, 50);
        allBooksFromReadingStatus.setBounds(80, 340, 250, 50);
        addBook.setBounds(80, 430, 250, 50);
        deleteBook.setBounds(80, 520, 250, 50);

        allBooks.setFont(new Font("Monospaced", Font.PLAIN, 12));
        allBooksFromGenre.setFont(new Font("Monospaced", Font.PLAIN, 12));
        allBooksFromReadingStatus.setFont(new Font("Monospaced", Font.PLAIN, 12));
        addBook.setFont(new Font("Monospaced", Font.PLAIN, 12));
        deleteBook.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

}
