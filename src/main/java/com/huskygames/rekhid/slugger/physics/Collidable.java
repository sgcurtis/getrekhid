package com.huskygames.rekhid.slugger.physics;

import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.util.Set;

public interface Collidable {
    Set<Shape> getCollisons();
}
