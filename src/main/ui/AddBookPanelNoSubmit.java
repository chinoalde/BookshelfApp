package ui;

import model.BookList;
import model.ReadingStatus;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AddBookPanelNoSubmit extends JPanel {
    private JLabel title;
    private JLabel author;
    private JLabel genre;
    private JLabel rating;
    private JLabel readingStatus;

    protected JFormattedTextField titleField;
    protected JFormattedTextField authorField;
    protected JComboBox selectGenre;
    protected JComboBox selectRating;
    protected JComboBox selectReadingStatus;

    protected JButton submit;
    protected JButton home;


    //EFFECT: constructs a panel with labels and fields
    public AddBookPanelNoSubmit(int width, int height, BookList bookList,
                                CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(Color.white);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        setUpLabels();
        labelsSetBounds();

        setInputs();
        inputsSetBounds();

        this.home = new JButton("home");
        this.home.setBounds(160, 650, 100, 50);
        this.home.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.home.addActionListener(e -> cards.show(cardLayoutPanel, "home page"));

        addToPanel();
    }

    //MODIFIES: title, author, genre, rating, reading status
    //EFFECTS: adds text to the labels and sets alignment/fonts
    private void setUpLabels() {
        this.title = new JLabel("title");
        this.author = new JLabel("author");
        this.genre = new JLabel("genre");
        this.rating = new JLabel("rating");
        this.readingStatus = new JLabel("reading status");

        setHorizontalAlignment();
        setFont();
    }

    //MODIFIES: this
    // centers the text of the labels horizontally
    private void setHorizontalAlignment() {
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.author.setHorizontalAlignment(JLabel.CENTER);
        this.genre.setHorizontalAlignment(JLabel.CENTER);
        this.rating.setHorizontalAlignment(JLabel.CENTER);
        this.readingStatus.setHorizontalAlignment(JLabel.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: sets fonts of the labels
    private void setFont() {
        this.title.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.author.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.genre.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.rating.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.readingStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
    }

    //MODIFIES: title, author, genre, rating, reading staus
    //EFFECTS: sets the bounds of the labels
    private void labelsSetBounds() {
        this.title.setBounds(140, 50, 140, 30);
        this.author.setBounds(140, 170, 140, 15);
        this.genre.setBounds(140, 280, 140, 15);
        this.rating.setBounds(140, 380, 140, 15);
        this.readingStatus.setBounds(140, 470, 140, 15);
    }

    //MODIFIES: titleField, authorField, selectGenre, selectRating, selectReadingStatus
    //EFFECTS: sets the text fields and the selection boxes
    private void setInputs() {
        String[] genres = {"Self-help", "Non-fiction", "Fiction", "Fantasy", "Action & Adventure"};
        Integer[] rating = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ReadingStatus[] status = {ReadingStatus.WANT_TO_READ, ReadingStatus.READING, ReadingStatus.READ};

        this.titleField = new JFormattedTextField();
        this.authorField = new JFormattedTextField();
        this.selectGenre = new JComboBox(genres);
        this.selectRating = new JComboBox(rating);
        this.selectReadingStatus = new JComboBox(status);

        this.titleField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.authorField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.selectGenre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.selectRating.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.selectReadingStatus.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    }

    //MODIFIES: titleField, authorField, selectGenre, selectRating, selectReadingStatus
    //EFFECTS: sets the bounds of the inputs
    private void inputsSetBounds() {
        this.titleField.setBounds(140, 80, 140, 30);
        this.authorField.setBounds(140, 190, 140, 30);
        this.selectGenre.setBounds(140, 300, 140, 30);
        this.selectRating.setBounds(140, 410, 140, 30);
        this.selectReadingStatus.setBounds(140, 520, 140, 30);
    }

    //MODIFIES: this
    //EFFECTS: adds all the elements to the panel
    private void addToPanel() {
        this.add(title);
        this.add(author);
        this.add(genre);
        this.add(rating);
        this.add(readingStatus);

        this.add(titleField);
        this.add(authorField);
        this.add(selectGenre);
        this.add(selectRating);
        this.add(selectReadingStatus);

        this.add(home);
    }
}
