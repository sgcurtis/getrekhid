package com.huskygames.rekhid.slugger.util;

import java.awt.*;

public class DoublePair {
    private double x, y;

    public DoublePair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void add(DoublePair pair) {
        x = x + pair.x;
        y = y + pair.y;
    }

    public void add(double scalar) {
        x = scalar + x;
        y = scalar + y;
    }

    public Point toPoint() {
        return new Point((int) x, (int) y);
    }
}
