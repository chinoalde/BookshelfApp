package ui;


import javax.swing.*;
import java.awt.*;

//represents a frame
public class Frame extends JFrame {
    Color background = new Color(215, 210, 206);

    public Frame(int width, int height) {
        this.setTitle("Bookshelf App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(width, height);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);

    }


}
