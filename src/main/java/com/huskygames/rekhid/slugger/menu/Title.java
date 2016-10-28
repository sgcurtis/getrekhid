package com.huskygames.rekhid.slugger.menu;


import com.huskygames.rekhid.Definitions;

import java.awt.*;

public class Title extends MenuItem {

    private final String title;
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

    public String getTitle() {
        return title;
    }
}
