package com.huskygames.rekhid.slugger.menu.items;


import com.huskygames.rekhid.Definitions;

import java.awt.*;

public class Title extends com.huskygames.rekhid.slugger.menu.items.MenuItem {

    private static String title;
    private final Font font;

    public Title(String title){
        this.title = title;
        font = Definitions.DISPLAY_FONT;
    }

    public Title(String title, Font font){
        this.title = title;
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    public static String getTitle() {
        return title;
    }
}
