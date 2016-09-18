package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.slugger.util.IntPair;
import javafx.util.Pair;

/**
 * The object that controls the viewing position and zoom for the matches.
 * Created by Kyle on 9/17/2016.
 */
public class ViewPort {

    //needs a height and the position of it's top-left corner
    //width is known once height is gained, due to known aspect ratio of 16:9
    int height;

    //the world's height, used for sanity checking
    int worldHeight;

    IntPair topLeft;


    public ViewPort(int theHeight, IntPair theCorner, int worldH) {
        height = theHeight;
        topLeft = theCorner;
        worldHeight = worldH;
    }


    /**
     * Updates the height of the viewport
     * @param newHeight: the proposed new height of the viewport
     * NOTE: The height of the viewport cannot exceed the height of the world.
     *       If the given height is too large, it will cap it down to the world's height.
     */
    public void update(int newHeight){
        if (newHeight > worldHeight)
            height = worldHeight;
        height = newHeight;
    }

    public void update(IntPair newCorner) {
        if(newCorner.getX() < 0)
            newCorner.setX(0);
        if(newCorner.getX() > ((worldHeight * 16 / 9) - (height * 16 / 9)))
            newCorner.setX(((worldHeight * 16 / 9) - (height * 16 / 9)));
        if(newCorner.getY() < 0)
            newCorner.setY(0);
        if(newCorner.getY() > (worldHeight - height))
            newCorner.setY((worldHeight - height));
        topLeft = newCorner;
    }

    public void update(int newHeight, IntPair newCorner) {
        if (newHeight > worldHeight)
            height = worldHeight;
        height = newHeight;

        if(newCorner.getX() < 0)
            newCorner.setX(0);
        if(newCorner.getX() > ((worldHeight * 16 / 9) - (height * 16 / 9)))
            newCorner.setX(((worldHeight * 16 / 9) - (height * 16 / 9)));
        if(newCorner.getY() < 0)
            newCorner.setY(0);
        if(newCorner.getY() > (worldHeight - height))
            newCorner.setY((worldHeight - height));
        topLeft = newCorner;
    }

    public int getHeight() {
        return height;
    }

    public IntPair getTopLeft() {
        return topLeft;
    }
}
