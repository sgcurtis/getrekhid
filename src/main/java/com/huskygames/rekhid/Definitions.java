package com.huskygames.rekhid;


import com.huskygames.rekhid.slugger.util.IntPair;

import java.awt.*;

public class Definitions {
    public static final int TARGET_FPS = 60;
    public static final float TARGET_TIME = 1000f / TARGET_FPS;

    public static final String NAME = "GET REKHI'D";

    public static final Rekhid.GameState DEFAULT_STATE = Rekhid.GameState.MENU;

    public static final int DEFAULT_WIDTH = 1280;
    public static final int DEFAULT_HEIGHT = 720;

    public static final IntPair DEFAULT_WORLD_SIZE = new IntPair(1280, 720);

    public static final boolean DRAW_CENTRES = true;
    public static final Color CENTRE_COLOR = new Color(0,255, 0, 100);

    public static final double MAXVPOS = 20;
    public static final double MAXVNEG = -20;

    public static final int DEFAULT_PLAYER_HEIGHT = 100;

    public static final double GRAVITY = 0.09;

    public static final boolean DRAW_HITBOXES = true;

    public static final Color HITBOX_COLOR = new Color(0, 100, 255, 100);
}
