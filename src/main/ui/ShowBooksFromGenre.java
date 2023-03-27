package ui;

import model.BookList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowBooksFromGenre extends ShowBooksPanel {
    private JLabel text;
    private JButton submit;
    private JComboBox selectGenre;
    private ArrayList<String> genres;

    public ShowBooksFromGenre(int width, int height, BookList bookList, CardLayout cards, JPanel cardLayoutPanel) {
        super(width, height, bookList, cards, cardLayoutPanel);

        this.text = new JLabel("Select a genre: ");
        this.text.setBounds(25, 578, 140, 40);
        this.text.setFont(new Font("Monospaced", Font.PLAIN, 12));

        String[] genres = {"Self-help", "Non-fiction", "Fiction", "Fantasy", "Action & Adventure"};

        this.selectGenre = new JComboBox(genres);
        this.selectGenre.setBounds(160, 580, 140, 40);
        this.selectGenre.setForeground(new Color(0,0,0));
        this.selectGenre.setFont(new Font("Monospaced", Font.PLAIN, 12));

        this.submit = new JButton("submit");
        this.submit.setBounds(310, 580, 80, 40);
        this.submit.setFont(new Font("Monospaced", Font.PLAIN, 12));

        addActions(bookList);

        this.add(text);
        this.add(submit);
        this.add(selectGenre);
    }

    // MODIFIES: submit
    // EFFECTS: upon button press, show only the books that belong to the selected category
    private void addActions(BookList bookList) {
        this.submit.addActionListener(e -> {
            books = bookList.getBooksFromGenre(selectGenre.getSelectedItem().toString());
            model.clear();
            loadList();
        });
    }





}
