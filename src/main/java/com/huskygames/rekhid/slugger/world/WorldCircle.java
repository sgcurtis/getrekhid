package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Circle;

public class WorldCircle extends Circle {
    private DoublePair center;
    private double r;

    public WorldCircle(DoublePair center, double r) {
        this.center = center;
        this.r = r;
    }

    @Override
    public DoublePair getPosition() {
        return center;
    }

    @Override
    public double getRadius() {
        return r;
    }
}
