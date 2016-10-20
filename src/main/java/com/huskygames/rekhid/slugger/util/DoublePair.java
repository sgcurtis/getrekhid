package com.huskygames.rekhid.slugger.util;

import java.awt.*;

import static com.huskygames.rekhid.slugger.util.NumberUtilities.max;
import static com.huskygames.rekhid.slugger.util.NumberUtilities.min;

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

    public void addInPlace(DoublePair pair) {
        x = x + pair.x;
        y = y + pair.y;
    }

    public void addInPlace(double scalar) {
        x = scalar + x;
        y = scalar + y;
    }

    public void addInPlace(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public Point toPoint() {
        return new Point((int) x, (int) y);
    }

    public DoublePair add(DoublePair pair) {
        DoublePair temp = new DoublePair(0, 0);
        temp.addInPlace(this);
        temp.addInPlace(pair);
        return temp;
    }

    public DoublePair subtract(DoublePair pair) {
        DoublePair temp = new DoublePair(0, 0);
        temp.addInPlace(this);
        temp.addInPlace(pair.neg());
        return temp;
    }

    public DoublePair subtract(IntPair pair) {
        DoublePair temp = new DoublePair(-pair.getX(), -pair.getY());
        temp.addInPlace(this);
        return temp;
    }

    public DoublePair neg() {
        return new DoublePair(-x, -y);
    }

    public DoublePair getMin(DoublePair other) {
        return new DoublePair(min(other.getX(), getX()), min(other.getY(), getY()));
    }

    public DoublePair getMax(DoublePair other) {
        return new DoublePair(max(other.getX(), getX()), max(other.getY(), getY()));
    }

    public boolean isInAabb(DoublePair a, DoublePair b) {
        DoublePair min = a.getMin(b);
        DoublePair max = a.getMax(b);

        if (this.getX() > min.getX() && this.getY() > min.getY()) {
            if (this.getX() < max.getX() && this.getY() < max.getY()) {
                return true;
            }
        }

        return false;
    }

    public void zeroInPlace() {
        x = 0.0;
        y = 0.0;
    }

    public DoublePair multiply(double scalar) {
        return new DoublePair(x * scalar, y * scalar);
    }

    public IntPair rounded() {
        return new IntPair((int) Math.round(x), (int) Math.round(y));
    }

    @Override
    public String toString() {
        return "DoublePair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
