package ui;

import model.Book;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        try {
            new BookshelfAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application. File not found.");
        }
    }


}
