package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.slugger.actor.Player;
import javafx.util.Pair;

/**
 * The world object, contains the grid, and all the objects on the grid.
 * Created by Kyle on 9/17/2016.
 */
public class World {

    //holds the height, in case it's not default
    //width is known no matter what, as aspect ratio is kept at 16:9
    private int height = Definitions.DEFAULT_HEIGHT;

    //private Grid grid;
    private Player[] players;

    //level contains its own colliders
    private Level level;

    private ViewPort viewPort;

    /**
     * Constructs the world object with default height and width
     * @param theLevel: The Level that will be running on this world
     * @param theView: The starting viewport used in this world.
     */
    public World(Level theLevel, int viewHeight, int viewTLX, int viewTLY) {
        //grid = new Grid(Definitions.DEFAULT_WIDTH, Definitions.DEFAULT_HEIGHT);
        players = new Player[4];
        for(int i = 0; i < 4; i++) {
            players[i] = null;
        }

        //defined level
        level = theLevel;

        //starting viewport
        viewPort = new ViewPort(viewHeight, new Pair<Integer, Integer>(viewTLX, viewTLY), height);
    }

    /**
     * Constructs the world using abnormal height
     * @param height: the custom height used in this world
     * @param theLevel: the level that will run on this world
     * @param theView: the starting viewport for this world
     */
    public World(int height, Level theLevel, int viewHeight, int viewTLX, int viewTLY) {
        //grid = new Grid(width, height);
        this.height = height;

        players = new Player[4];
        for(int i = 0; i < 4; i++) {
            players[i] = null;
        }
        level = theLevel;

        viewPort = new ViewPort(viewHeight, new Pair<Integer, Integer>(viewTLX, viewTLY), height);
    }


    /**
     * Returns the scale of the view, based on the height of the viewport and the height of the world
     * @return: a double of the scale of the viewport to the world
     * NOTE: the scale should never be less than 1. This is strictly to blow up the image when the viewport is zoomed in.
     * The largest the viewport can be is the size of the world itself.
     */
    public double scaleView() {
        return height / viewPort.getHeight();
    }
}
