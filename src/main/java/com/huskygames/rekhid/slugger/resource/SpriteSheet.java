package com.huskygames.rekhid.slugger.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Sprite sheets will be created for each character, and will be in "PNG" format. A .txt file with
 * the same name in the same directory will define the image parts in a schema defined as: TODO
 *
 */
public class SpriteSheet extends LoadedImage {

    private int spriteHeight;
    private int spriteWidth;
    private int spriteCountWidth;
    private int spriteCountHeight;

    private BufferedImage[][] sprites;

    public SpriteSheet(String path, InputStream image, InputStream definition) {
        super(path, image);
        Properties properties = new Properties();
        try {
            properties.load(SpriteSheet.class.getResourceAsStream
                            (path.substring(0, path.length() - 5) + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            spriteHeight = Integer.parseInt(properties.getProperty("spriteheight"));
            spriteWidth = Integer.parseInt(properties.getProperty("spritewidth"));
            spriteCountHeight = Integer.parseInt(properties.getProperty("spritecountheight"));
            spriteCountWidth = Integer.parseInt(properties.getProperty("spritecountwidth"));
        } catch (NumberFormatException | NullPointerException ex) {
            spriteWidth = -1;
            spriteHeight = -1;
            spriteCountWidth = -1;
            spriteCountHeight = -1;
            logger.error("Could not load sprite sheet for: " + path, ex);
            return;
        }


    }
}
