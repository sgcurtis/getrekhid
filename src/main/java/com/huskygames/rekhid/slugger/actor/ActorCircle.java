package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Circle;

public class ActorCircle extends Circle {
    private DoublePair offset;
    private Actor parent;

    private double r;


    @Override
    public DoublePair getCenter() {
        return parent.getPosition().add(offset);
    }

    @Override
    public double getRadius() {
        return r;
    }
}
