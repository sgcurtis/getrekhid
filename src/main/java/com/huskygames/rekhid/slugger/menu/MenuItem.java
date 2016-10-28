package com.huskygames.rekhid.slugger.menu;

import com.huskygames.rekhid.slugger.Drawable;

public abstract class MenuItem {

    protected double percentageHeight = FIT_TO_PARENT;
    protected double percentageWidth = FIT_TO_PARENT;
    protected double height = USE_DINAMIC_SIZE;
    protected double width = USE_DINAMIC_SIZE;
    public static final double FIT_TO_PARENT = -1;
    public static final double USE_DINAMIC_SIZE = -2;

    public MenuItem() {

    }

    public double getPercentageHeight() {
        return percentageHeight;
    }

    public void setPercentageHeight(double percentageHeight) {
        this.percentageHeight = percentageHeight;
    }

    public double getPercentageWidth() {
        return percentageWidth;
    }

    public void setPercentageWidth(double percentageWidth) {
        this.percentageWidth = percentageWidth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

}
