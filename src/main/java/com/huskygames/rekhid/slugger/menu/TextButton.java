package com.huskygames.rekhid.slugger.menu;

import java.awt.*;

public class TextButton implements MenuItem {

    private final String text;
    private final int x, y;


    public TextButton(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D context) {

    }
}
