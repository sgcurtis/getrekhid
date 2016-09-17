package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.slugger.actor.Player;

/**
 * The world object, contains the grid, and all the objects on the grid.
 * Created by Kyle on 9/17/2016.
 */
public class World {

    private Grid grid;
    private Player[] players;
    private Level level;

    public World() {
        grid = new Grid(Definitions.DEFAULT_WIDTH, Definitions.DEFAULT_HEIGHT);
        players = new Player[4];
        for(int i = 0; i < 4; i++) {
            players[i] = null;
        }
    }

    public World(int width, int height) {
        grid = new Grid(width, height);
        players = new Player[4];
        for(int i = 0; i < 4; i++) {
            players[i] = null;
        }
    }

}
