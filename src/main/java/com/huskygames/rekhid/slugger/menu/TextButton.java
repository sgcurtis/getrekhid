package com.huskygames.rekhid.slugger.menu;

import com.huskygames.rekhid.Definitions;

import java.awt.*;

public class TextButton extends MenuItem {

    private final String text;
    private final Font font;

    public TextButton(String text) {
        this.text = text;
        font = Definitions.DISPLAY_FONT;
    }

    public TextButton(String text, Font font) {
        this.text = text;
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }


}
