package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.slugger.physics.Collidable;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * Stores all the level-specific information for a given level
 */
public abstract class Level implements Collidable {

    protected Resource background;
    protected Set<Shape> colliders;
    protected Resource music;
    protected IntPair[] startPos;
    protected ViewPort defaultViewPort;

    public IntPair getLevelSize() {
        return levelSize;
    }

    protected IntPair levelSize;

    public ViewPort getDefaultViewPort() {
        return defaultViewPort;
    }

    public Resource getBackground(){
        return background;
    }

    public abstract BufferedImage getBackgroundImage();

    public Set<Shape> getCollisions() {
        return colliders;
    }
    public void setColliders(Set<Shape> cols) {
        colliders = cols;
    }

    public Resource getMusic() {
        return music;
    }

    public IntPair[] getStartPos() {
        return startPos;
    }

    public abstract IntPair getMinViewPort();
    public abstract IntPair getMaxViewPort();

    public abstract int getMinViewHeight();
}
