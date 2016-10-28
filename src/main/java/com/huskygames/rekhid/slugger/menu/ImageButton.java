package com.huskygames.rekhid.slugger.menu;

import com.huskygames.rekhid.slugger.resource.Resource;

public class ImageButton extends MenuItem {

    private final Resource image;

    public ImageButton(Resource image) {
        this.image = image;
    }

    public Resource getImage() {
        return image;
    }


}
