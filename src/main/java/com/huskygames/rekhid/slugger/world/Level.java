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

    public abstract IntPair getLevelSize();

    public abstract ViewPort getDefaultViewPort();

    public abstract Resource getBackground();

    public abstract BufferedImage getBackgroundImage();

    public abstract Set<Shape> getCollisions();

    public abstract void setColliders(Set<Shape> cols);

    public abstract Resource getMusic();

    public abstract IntPair[] getStartPos();

    public abstract IntPair getMinViewPort();

    public abstract IntPair getMaxViewPort();

    public abstract int getMinViewHeight();
}
