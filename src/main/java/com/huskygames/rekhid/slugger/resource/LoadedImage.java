package com.huskygames.rekhid.slugger.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadedImage extends LoadedResource {

    protected static final Logger logger = LogManager.getLogger(LoadedImage.class.getName());
    protected BufferedImage image;

    public LoadedImage(String path, InputStream file) {
        super(path);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            logger.warn("Failed to open image for: " + path + " using default texture.", e);
            // TODO : default texture
            image = null;
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
