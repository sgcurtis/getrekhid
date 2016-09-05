package com.huskygames.rekhid.slugger.menu;

import com.huskygames.rekhid.slugger.Drawable;

import java.awt.*;
import java.util.HashSet;

public class Menu implements Drawable {
    private final HashSet<MenuItem> items = new HashSet<>();

    private Image background;

    public void draw(Graphics2D context) {
        for (MenuItem item : items) {
            item.draw(context);
        }

        if (background != null) {
            context.drawImage(background, 0, 0, null);
        }
    }

    public void setBackground(Image background) {
        this.background = background;
    }
}
