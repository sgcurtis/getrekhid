package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Circle;

public class ActorCircle extends Circle {
    public DoublePair getOffset() {
        return offset;
    }

    private DoublePair offset;
    private Actor parent;

    private double r;

    public ActorCircle(DoublePair offset, Actor parent, double r) {
        this.offset = offset;
        this.parent = parent;
        if (r < 0) {
            throw new IllegalArgumentException("Radius cannot be negative!");
        }
        this.r = r;
    }

    @Override
    public DoublePair getPosition() {
        return parent.getPosition().add(offset);
    }

    @Override
    public double getRadius() {
        return r;
    }
}
