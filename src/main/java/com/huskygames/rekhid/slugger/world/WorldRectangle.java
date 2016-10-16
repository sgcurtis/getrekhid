package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Rectangle;

public class WorldRectangle extends Rectangle {
    private DoublePair pt1;
    private DoublePair pt2;

    @Override
    public String toString() {
        return "WorldRectangle{" +
                "pt1=" + pt1 +
                ", pt2=" + pt2 +
                '}';
    }

    public WorldRectangle(double x1, double y1, double x2, double y2) {
        pt1 = new DoublePair(x1, y1);
        pt2 = new DoublePair(x2, y2);
    }

    @Override
    public DoublePair getPosition() {
        return pt1;
    }

    @Override
    public DoublePair getPt1() { return pt1;  }

    @Override
    public DoublePair getPt2() {
        return pt2;
    }
}
