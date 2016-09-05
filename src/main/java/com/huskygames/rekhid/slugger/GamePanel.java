package com.huskygames.rekhid.slugger;

import com.huskygames.rekhid.Definitions;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(Definitions.DEFAULT_WIDTH, Definitions.DEFAULT_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
    }
}
