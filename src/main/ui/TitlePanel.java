package ui;

import javax.swing.*;
import java.awt.*;

// represents a TitlePanel
public class TitlePanel extends JPanel {

    public TitlePanel(int width, int height) {
        this.setBackground(Color.white);
        this.setBounds(0, 0, width, (int) (height * 0.05));

        JLabel title = new JLabel();
        title.setText("Bookshelf");
//        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(new Color(0, 0, 0));
        title.setFont(new Font("SansSerif",Font.BOLD, 24));

        this.add(title);
    }


}
