package com.huskygames.rekhid.slugger.resource;

import com.huskygames.rekhid.slugger.resource.sprite.SpriteSheet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Manages the loading of resources in the jarfile. One for the main thread, and another
 * for the audio thread.
 */
public class ResourceManager {

    private final static Logger logger = LogManager.getLogger(ResourceManager.class);

    private HashMap<Resource, LoadedResource> cache = new HashMap<>();

    public ResourceManager() {
    }

    public void suggestLoad(Resource res) {
        // TODO: for now, this just loads it normally. Make this smarter
        requestResource(res);
    }

    /**
     * Returns a resource from the cache, or forces it to be loaded immediately.
     *
     * @param res the resource to request
     */
    public LoadedResource requestResource(Resource res) {
        LoadedResource cachedResource = cache.get(res);
        if (cachedResource != null) {
            return cachedResource;
        }

        InputStream stream = ResourceManager.class.getClassLoader().getResourceAsStream(res.location);

        LoadedResource temp;

        if (res.type == Resource.Type.IMAGE) {
            temp = new LoadedImage(res.location, stream);
        } else if (res.type == Resource.Type.SPRITE_SHEET) {
            String path = res.location;
            String defPath = path.substring(0, path.length() - 4) + ".properties";
            InputStream defs = ResourceManager.class.getClassLoader().getResourceAsStream(defPath);
            temp = new SpriteSheet(path, stream, defs);
        } else if (res.type == Resource.Type.MUSIC) {
            try {

                AudioInputStream inputStream = AudioSystem.getAudioInputStream(stream);

                temp = new AudioFile(res.location, inputStream);
            } catch (UnsupportedAudioFileException | IOException e) {
                logger.warn("Unable to open audio: " + res.location, e);
                temp = null;
            }
        } else {
            // TODO: addInPlace support for audio
            throw new UnsupportedOperationException("Unrecognized resource type.");
        }

        if (temp != null) {
            cache.put(res, temp);
        }

        return temp;
    }

}
