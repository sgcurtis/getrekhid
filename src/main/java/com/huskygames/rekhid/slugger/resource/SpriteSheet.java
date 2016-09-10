package com.huskygames.rekhid.slugger.resource;

import java.io.InputStream;

/**
 * Sprite sheets will be created for each character, and will be in "PNG" format. A .txt file with
 * the same name in the same directory will define the image parts in a schema defined as: TODO
 *
 */
public class SpriteSheet extends LoadedImage {

    public SpriteSheet(String path, InputStream image, InputStream definition) {
        super(path, image);
        // TODO make this a spritesheet
    }
}
