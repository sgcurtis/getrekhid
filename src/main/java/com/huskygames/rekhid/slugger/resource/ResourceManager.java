package com.huskygames.rekhid.slugger.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Manages the loading of resources in the jarfile. One for the main thread, and another
 * for the audio thread.
 */
public class ResourceManager {

    private final static Logger logger = LogManager.getLogger(ResourceManager.class);

    private HashMap<Resource, LoadedResource> cache = new HashMap<>();

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
        logger.warn("Resource location is: " + res.location);
        InputStream stream = ResourceManager.class.getClassLoader().getResourceAsStream(res.location);

        LoadedResource temp;
        if (res.type == Resource.Type.IMAGE) {
            temp = new LoadedImage(res.location, stream);
        }
        else if (res.type == Resource.Type.SPRITE_SHEET) {
            // TODO: implement a sprite sheet and make it this
            temp = new LoadedImage(res.location, stream);
        }
        else {
            // TODO: add support for audio
            throw new UnsupportedOperationException("Unrecognized resource type.");
        }

        return temp;
    }

}
