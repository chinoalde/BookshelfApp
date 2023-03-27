package ui;

import javax.swing.*;
import java.awt.*;

//represents a StartUpPanel with no start button
public class StartUpPanelNoStartButton extends JPanel {
    private ImageIcon img;
    private JLabel imgLabel;

    //constructs a StartUpPanelNoStartButton
    public StartUpPanelNoStartButton(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(Color.WHITE);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        // image source: https://www.pinterest.ca/pin/673428950508064913/
        this.img = new ImageIcon("./data/ReadingGIF.gif");
        this.imgLabel = new JLabel(img);
        this.imgLabel.setHorizontalAlignment(JLabel.CENTER);

        this.imgLabel.setBounds(0, 90, width, 400);

        this.add(imgLabel);

    }

}
