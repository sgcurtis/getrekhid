package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.slugger.physics.Collidable;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.shape.Shape;

import java.util.Set;

/**
 * Stores all the level-specific information for a given level
 * Created by Kyle on 9/17/2016.
 */
public abstract class Level implements Collidable {

    private LoadedImage background;
    Set<Shape> colliders;
    Resource bgm;
}
