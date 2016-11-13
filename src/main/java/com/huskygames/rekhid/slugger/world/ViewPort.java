package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.util.IntPair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The object that controls the viewing position and zoom for the matches.
 */
public class ViewPort {

    //needs a height and the position of it's top-left corner
    //width is known once height is gained, due to known aspect ratio of 16:9
    private int height;
    //the world's height, used for sanity checking
    private int worldHeight;
    private IntPair topLeft;

    private Logger logger = LogManager.getLogger(World.class.getName());

    public ViewPort(int height, IntPair corner, int worldHeight) {
        this.height = height;
        topLeft = corner;
        this.worldHeight = worldHeight;
    }

    @Override
    public String toString() {
        return "ViewPort{" +
                "height=" + height +
                ", topLeft=" + topLeft +
                '}';
    }

    /**
     * Updates the height of the viewport
     *
     * @param newHeight: the proposed new height of the viewport
     *                   NOTE: The height of the viewport cannot exceed the height of the world.
     *                   If the given height is too large, it will cap it down to the world's height.
     */
    public void update(int newHeight) {
        if (newHeight > worldHeight)
            height = worldHeight;
        height = newHeight;
    }

    public void setCorner(IntPair newCorner) {

        IntPair ULPlayable = Rekhid.getInstance().getWorld().getLevel().getUpperLeftPlayableArea();
        IntPair LRPlayable = Rekhid.getInstance().getWorld().getLevel().getLowerRightPlayableArea();

        int minX = ULPlayable.getX();
        int maxX = LRPlayable.getX() - (height * 16 / 9);
        int minY = ULPlayable.getY();
        int maxY = LRPlayable.getY() - height;


        if (newCorner.getX() > maxX) {
            newCorner.setX(maxX);
        }

        if (newCorner.getY() > maxY) {
            newCorner.setY(maxY);
        }
        if (newCorner.getX() < minX) {
            newCorner.setX(minX);
        }

        if (newCorner.getY() < minY) {
            newCorner.setY(minY);
        }

        topLeft = newCorner;
    }

    public void setCorner(int newHeight, IntPair newCorner) {
        if (newHeight > worldHeight) {
            height = worldHeight;
        }
        height = newHeight;

        if (newCorner.getX() < 0) {

            newCorner.setX(0);
        }
        if (newCorner.getX() > ((worldHeight * 16 / 9) - (height * 16 / 9))) {
            newCorner.setX(((worldHeight * 16 / 9) - (height * 16 / 9)));
        }
        if (newCorner.getY() < 0) {
            newCorner.setY(0);
        }
        if (newCorner.getY() > (worldHeight - height)) {
            newCorner.setY((worldHeight - height));
        }
        topLeft = newCorner;
    }

    /**
     * The current height of the viewport, in px
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public IntPair getTopLeft() {
        return topLeft;
    }

    /**
     * Returns the size of the Canvas/Window in pixels
     *
     * @return
     */
    public IntPair getWindowSize() {
        return new IntPair(Rekhid.getInstance().getWidth(),
                Rekhid.getInstance().getHeight());
    }
}
