package com.huskygames.rekhid.slugger.resource;

import com.huskygames.rekhid.slugger.resource.sprite.SpriteSheet;
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
        LoadedResource cachedResource = cache.get(res);
        if (cachedResource != null) {
            return cachedResource;
        }

        InputStream stream = ResourceManager.class.getClassLoader().getResourceAsStream(res.location);

        LoadedResource temp;

        if (res.type == Resource.Type.IMAGE) {
            temp = new LoadedImage(res.location, stream);
        }
        else if (res.type == Resource.Type.SPRITE_SHEET) {
            String path = res.location;
            InputStream defs = SpriteSheet.class.getResourceAsStream
                    (path.substring(0, path.length() - 5) + ".properties");
            temp = new SpriteSheet(path, stream, defs);
        }
        else {
            // TODO: addInPlace support for audio
            throw new UnsupportedOperationException("Unrecognized resource type.");
        }

        if (temp != null) {
            cache.put(res, temp);
        }

        return temp;
    }

}
