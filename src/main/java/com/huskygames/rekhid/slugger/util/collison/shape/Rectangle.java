package com.huskygames.rekhid.slugger.util.collison.shape;

import com.huskygames.rekhid.slugger.util.DoublePair;

public abstract class Rectangle implements Shape {
    public abstract DoublePair getPosition();
    public abstract DoublePair getPt1();
    public abstract DoublePair getPt2();

    public DoublePair getMax() {
        return getPt1().getMax(getPt2());
    }

    public DoublePair getMin() {
        return getPt1().getMin(getPt2());
    }
}
