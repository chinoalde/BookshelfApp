package ui;

import model.BookList;
import model.ReadingStatus;

import javax.swing.*;
import java.awt.*;

public class ShowBooksByReadingStatusPanel extends ShowBooksPanel {
    private JLabel text;
    private JButton submit;
    private JComboBox selectReadingStatus;

    public ShowBooksByReadingStatusPanel(int width, int height, BookList bookList,
                                         CardLayout cards, JPanel cardLayoutPanel) {
        super(width, height, bookList, cards, cardLayoutPanel);

        this.text = new JLabel("Select a reading status: ");
        this.text.setBounds(25, 578, 140, 40);
        this.text.setFont(new Font("Monospaced", Font.PLAIN, 12));

        ReadingStatus[] status = {ReadingStatus.WANT_TO_READ, ReadingStatus.READING, ReadingStatus.READ};

        this.selectReadingStatus = new JComboBox(status);
        this.selectReadingStatus.setBounds(160, 580, 140, 40);
        this.selectReadingStatus.setForeground(new Color(0,0,0));
        this.selectReadingStatus.setFont(new Font("Monospaced", Font.PLAIN, 12));

        this.submit = new JButton("submit");
        this.submit.setBounds(310, 580, 80, 40);
        this.submit.setFont(new Font("Monospaced", Font.PLAIN, 12));

        addActions(bookList);

        this.add(text);
        this.add(submit);
        this.add(selectReadingStatus);
    }

    // MODIFIES: submit
    // EFFECTS: upon button press, show only the books that belong to the selected category
    private void addActions(BookList bookList) {
        this.submit.addActionListener(e -> {
            books = bookList.getBooksByReadingStatus((ReadingStatus) selectReadingStatus.getSelectedItem());
            model.clear();
            loadList();
        });
    }
}
