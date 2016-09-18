package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.slugger.actor.Player;

/**
 * The world object, contains the grid, and all the objects on the grid.
 * Created by Kyle on 9/17/2016.
 */
public class World {

    //holds the height and width, in case it's not default
    private int height = Definitions.DEFAULT_HEIGHT;
    private int width = Definitions.DEFAULT_WIDTH;

    //private Grid grid;
    private Player[] players;

    //level contains its own colliders
    private Level level;

    //scale
    private double scale;

    private ViewPort viewPort;

    public World(Level theLevel, ViewPort theView) {
        //grid = new Grid(Definitions.DEFAULT_WIDTH, Definitions.DEFAULT_HEIGHT);
        players = new Player[4];
        for(int i = 0; i < 4; i++) {
            players[i] = null;
        }

        //defined level
        level = theLevel;

        //starting viewport
        viewPort = theView;
    }

    public World(int width, int height, Level theLevel) {
        //grid = new Grid(width, height);
        players = new Player[4];
        for(int i = 0; i < 4; i++) {
            players[i] = null;
        }
        level = theLevel;
    }
}
