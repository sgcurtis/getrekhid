package com.huskygames.rekhid.slugger.world;

/**
 * Grid object to place objects on and around
 * Created by Kyle on 9/17/2016.
 */
public class Grid {
    private int gridWidth;
    private int gridHeight;

    public Grid(int width, int height) {
        gridWidth = width;
        gridHeight = height;
    }

    public void resize(int width, int height) {
        gridWidth = width;
        gridHeight = height;
    }
}
