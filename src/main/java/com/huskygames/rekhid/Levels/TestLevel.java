package com.huskygames.rekhid.Levels;

import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;
import com.huskygames.rekhid.slugger.world.Level;

import java.util.Set;

/**
 * Created by Kyle on 9/17/2016.
 */
public class TestLevel extends Level {


    public TestLevel() {
        setBackground(Resource.TEST_BACKGROUND);
        setBgm(Resource.MAIN_MENU_BG);
        //Set<Shape> cols = new Set<Shape>();
        //setColliders();

        IntPair[] starts = new IntPair[4];
        starts[0] = new IntPair(300, 550);
        starts[1] = new IntPair(600, 550);
        starts[2] = new IntPair(900, 550);
        starts[3] = new IntPair(1200, 550);

        setStartPos(starts);
    }
}
