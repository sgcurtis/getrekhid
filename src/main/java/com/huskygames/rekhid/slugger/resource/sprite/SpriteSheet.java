package com.huskygames.rekhid.slugger.resource.sprite;

import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.util.NumberUtilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Sprite sheets will be created for each character, and will be in "PNG" format. A .properties
 * file withthe same name in the same directory will define the image parts in a schema defined as:
 *
 * spriteheight:
 * spritewidth:
 * width:
 * height:
 * spritecountwidth:
 * spritecountheight:
 *
 */
public class SpriteSheet extends LoadedImage {

    private int spriteHeight;
    private int spriteWidth;
    private int spriteCountWidth;
    private int spriteCountHeight;
    private int width;
    private int height;

    private BufferedImage[][] sprites;

    public SpriteSheet(String path, InputStream imageStream, InputStream definition) {
        super(path, imageStream);
        Properties properties = new Properties();
        try {
            properties.load(definition);

            spriteHeight = Integer.parseInt(properties.getProperty("spriteheight"));
            spriteWidth = Integer.parseInt(properties.getProperty("spritewidth"));
            spriteCountHeight = Integer.parseInt(properties.getProperty("spritecountheight"));
            spriteCountWidth = Integer.parseInt(properties.getProperty("spritecountwidth"));
            width = Integer.parseInt(properties.getProperty("width"));
            height = Integer.parseInt(properties.getProperty("height"));

        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            spriteWidth = -1;
            spriteHeight = -1;
            spriteCountWidth = -1;
            spriteCountHeight = -1;
            width = -1;
            height = -1;

            logger.error("Could not load sprite sheet for: " + path, ex);
            return;
        }

        sprites = new BufferedImage[spriteCountWidth][spriteCountHeight];

        for (int i = 0; i < spriteCountWidth; i++) {
            for (int j = 0; j < spriteCountHeight; j++) {
                int startX = i * spriteWidth;
                int startY = j * spriteHeight;
                sprites[i][j] = image.getSubimage(startX, startY,
                        NumberUtilities.truncate(startX + spriteWidth, width),
                        NumberUtilities.truncate(startY + spriteHeight, height));
            }
        }
    }

    public BufferedImage getSprite(int x, int y) {
        return sprites[x][y];
    }
}
