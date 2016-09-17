package com.huskygames.rekhid;

import com.huskygames.rekhid.slugger.menu.Menu;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;

public class MainMenu extends Menu {

    private final Rekhid parent;

    public MainMenu(Rekhid parent) {
        this.parent = parent;

        LoadedImage background = (LoadedImage) parent.getResourceManager().requestResource(Resource.MAIN_MENU_BG);
        setBackground(background.getImage());
    }
}
