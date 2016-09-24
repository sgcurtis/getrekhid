package com.huskygames.rekhid.slugger.util;

public class IntPair {
    private int x;
    private int y;

    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        y = newY;
    }

    public IntPair neg() {
        return new IntPair(-x, -y);
    }

    public DoublePair mul(double scalar) {
        return new DoublePair(x * scalar, y * scalar);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public IntPair subtract(IntPair minR) {
        return new IntPair(x - minR.getX(), y - minR.getY());
    }
}
