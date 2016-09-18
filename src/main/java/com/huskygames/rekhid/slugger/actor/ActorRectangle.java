package com.huskygames.rekhid.slugger.actor;


import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Rectangle;

public class ActorRectangle extends Rectangle {
    private DoublePair relPt1;
    private DoublePair relPt2;
    private Actor parent;

    @Override
    public DoublePair getPt1() {
        return parent.getPosition().add(relPt1);
    }

    @Override
    public DoublePair getPt2() {
        return parent.getPosition().add(relPt2);
    }
}
