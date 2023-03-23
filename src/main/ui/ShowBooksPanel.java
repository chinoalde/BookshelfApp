package ui;

import model.Book;
import model.BookList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowBooksPanel extends JPanel {
    protected JList<String> jlist;
    protected DefaultListModel<String> model;
    protected List<Book> books;
    protected JScrollPane scroll;
    protected JButton home;

    public ShowBooksPanel(int width, int height, BookList bookList,
                          CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(Color.green);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        this.jlist = new JList<>();
        this.model = new DefaultListModel<>();

        this.books = bookList.getAllBooks();
        this.scroll = new JScrollPane(jlist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.home = new JButton("home");
        this.home.setBounds(160, 650, 100, 50);
        this.home.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.home.addActionListener((e -> cards.show(cardLayoutPanel, "Home Page")));

        loadList();

        this.jlist.setModel(model);
        this.jlist.setSelectedIndex(0);
        this.scroll.setBounds(0, 10, (int) (width * 0.95), (int) (height * 0.7));
        this.jlist.setFont(new Font("Monospaced", Font.PLAIN, 10));

        this.add(scroll);
        this.add(home);

    }


    //MODIFIES: model
    //EFFECTS: fills model with book titles
    public void loadList() {
        for (Book b : books) {
            model.addElement(b.getTitle());
        }
    }

    //MODIFIES: model
    //EFFECTS: resets and fills model with new books after clearing
    public void refresh(List<Book> newBooks) {
        model.clear();
        for (Book b : newBooks) {
            model.addElement(b.getTitle());
        }
    }
}
