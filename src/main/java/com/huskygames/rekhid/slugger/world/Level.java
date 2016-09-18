package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.slugger.physics.Collidable;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.util.Set;

/**
 * Stores all the level-specific information for a given level
 * Created by Kyle on 9/17/2016.
 */
public abstract class Level implements Collidable {

    private Resource background;
    private Set<Shape> colliders;
    private Resource bgm;
    private IntPair[] startPos;

    public Resource getBackground(){
        return background;
    }
    public void setBackground(Resource bg) {
        background = bg;
    }

    public Set<Shape> getCollisions() {
        return colliders;
    }
    public void setColliders(Set<Shape> cols) {
        colliders = cols;
    }

    public Resource getBgm() {
        return bgm;
    }
    public void setBgm(Resource bgm) {
        this.bgm = bgm;
    }

    public IntPair[] getStartPos() {
        return startPos;
    }
    public void setStartPos(IntPair[] startPos) {
        this.startPos = startPos;
    }
}
