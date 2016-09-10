package com.huskygames.rekhid.slugger;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final Main parent;

    public GamePanel(Main parent) {
        this.parent = parent;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(Definitions.DEFAULT_WIDTH, Definitions.DEFAULT_HEIGHT);
    }

    private long lastMs;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // terrible lock contention, but it sure is easy
        synchronized (parent.getGameLock()) {
            // ALL GAME DRAWING CODE MUST BE IN HERE


            if (parent.getGameState() == Main.GameState.MENU) {
                parent.getMainMenu().draw((Graphics2D) g);
            }

            // Draw FPS
            g.drawString((int) (1000f / parent.getLastTickTime()) + " FPS", this.getWidth() - 60, 15);

            lastMs = System.currentTimeMillis();
        }
    }
}
