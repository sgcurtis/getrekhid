package com.huskygames.rekhid.slugger.resource;

public enum Resource {

    // BEGIN RESOURCE LIST

    MAIN_MENU_BG("images/menu1.jpg", Type.IMAGE),
    MENU_BG_MUSIC("meganddia.mp3", Type.MUSIC);

    // END RESOURCE LIST

    final Type type;
    final String location;

    public enum Type {
        MUSIC,
        CLIP,
        IMAGE,
        SPRITE_SHEET,
        SPRITE_SHEET_DEFINITION
    }

    Resource(String location, Type type) {
        this.location = location;
        this.type = type;
    }
}
