package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.awt.image.BufferedImage;
import java.util.HashSet;

public class LevelTerminal extends Level {

    private final IntPair minView = new IntPair(500, 100);
    private final IntPair maxView = new IntPair(3000, 1500);
    private final int minViewHeight = 300;

    public LevelTerminal() {
        background = Resource.LEVEL_TERMINAL_BG;
        levelSize = Definitions.DEFAULT_WORLD_SIZE;
        defaultViewPort = new ViewPort(1500, new IntPair(700, 200), levelSize.getY());
        colliders = new HashSet<Shape>();
        colliders.add(new WorldRectangle(0,500, 7000,1000));
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return ((LoadedImage) Rekhid.getInstance().getResourceManager()
                .requestResource(background)).getImage();
    }

    @Override
    public IntPair getMinViewPort() {
        return minView;
    }

    @Override
    public IntPair getMaxViewPort() {
        return maxView;
    }

    @Override
    public int getMinViewHeight() {
        return 100;
    }
}
