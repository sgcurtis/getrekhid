package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.util.IntPair;
import javafx.util.Pair;

/**
 * The object that controls the viewing position and zoom for the matches.
 */
public class ViewPort {

    //needs a height and the position of it's top-left corner
    //width is known once height is gained, due to known aspect ratio of 16:9
    int height;

    //the world's height, used for sanity checking
    int worldHeight;

    IntPair topLeft;

    @Override
    public String toString() {
        return "ViewPort{" +
                "height=" + height +
                ", topLeft=" + topLeft +
                '}';
    }

    public ViewPort(int height, IntPair corner, int worldHeight) {
        this.height = height;
        topLeft = corner;
        this.worldHeight = worldHeight;
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

    public void setCorner(IntPair newCorner) {
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

    public void setCorner(int newHeight, IntPair newCorner) {
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

    public IntPair getWindowSize() {
        return new IntPair(Rekhid.getInstance().getWidth(),
                Rekhid.getInstance().getHeight());
    }
}
