package com.huskygames.rekhid.slugger.world;

import javafx.util.Pair;

/**
 * The object that controls the viewing position and zoom for the matches.
 * Created by Kyle on 9/17/2016.
 */
public class ViewPort {

    //needs a height and the position of it's top-left corner
    //width is known once height is gained, due to known aspect ratio of 16:9
    int height;
    Pair<Integer, Integer> topLeft;


    public ViewPort(int theHeight, Pair<Integer, Integer> theCorner) {
        height = theHeight;
        topLeft = theCorner;
    }


    public void update(int newHeight){
        height = newHeight;
    }

    public void update(Pair<Integer, Integer> newCorner) {
        topLeft = newCorner;
    }

    public void update(int newHeight, Pair<Integer, Integer> newCorner) {
        height = newHeight;
        topLeft = newCorner;
    }

    public int getHeight() {
        return height;
    }

    public Pair<Integer, Integer> getTopLeft() {
        return topLeft;
    }
}
