package com.huskygames.rekhid;

import com.huskygames.rekhid.slugger.menu.Menu;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends Menu {

    private final Main parent;

    public MainMenu(Main parent) {
        this.parent = parent;

        LoadedImage background = (LoadedImage) parent.getResourceManager().requestResource(Resource.MAIN_MENU_BG);
        setBackground(background.getImage());
    }
}
