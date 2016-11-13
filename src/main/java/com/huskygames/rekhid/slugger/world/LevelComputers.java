package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class LevelComputers extends Level {

    private final IntPair minView = new IntPair(1000, 100);
    private final IntPair maxView = new IntPair(3460, 1160);
    private final IntPair minPlayable = new IntPair(0, 0);
    private final IntPair maxPlayable = new IntPair(4060, 2000);

    private final int minViewHeight = 300;
    protected IntPair levelSize;
    protected Resource background;
    protected Set<Shape> colliders = new HashSet<Shape>();
    protected Resource music;
    protected IntPair[] startPos;
    protected ViewPort defaultViewPort;

    public LevelComputers() {
        background = Resource.LEVEL_COMPUTERS_BG;
        levelSize = Definitions.DEFAULT_WORLD_SIZE;
        defaultViewPort = new ViewPort(1500, new IntPair(700, 200), levelSize.getY());
        startPos = new IntPair[] {new IntPair(2150, 1),
                new IntPair(2550, 1),
                new IntPair(700, 1),
                new IntPair(700, 1)};
        createDefaultColliders();
    }

    private void createDefaultColliders() {
        colliders.add(new WorldRectangle(0, 1000, 7000, 1000));
        colliders.add(new WorldRectangle(1625, 900, 1875, 750));
        colliders.add(new WorldRectangle(0, 790, 1150, 955));
    }

    @Override
    public IntPair getLevelSize() {
        return levelSize;
    }

    @Override
    public ViewPort getDefaultViewPort() {
        return defaultViewPort;
    }

    @Override
    public Resource getBackground() {
        return background;
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return ((LoadedImage) Rekhid.getInstance().getResourceManager()
                .requestResource(background)).getImage();
    }

    @Override
    public Set<Shape> getCollisions() {
        return colliders;
    }

    @Override
    public void setColliders(Set<Shape> cols) {
        colliders = cols;
    }

    @Override
    public Resource getMusic() {
        return music;
    }

    @Override
    public IntPair[] getStartPos() {
        return startPos;
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

    @Override
    public IntPair getLowerRightPlayableArea() {
        return maxPlayable;
    }

    @Override
    public IntPair getUpperLeftPlayableArea() {
        return minPlayable;
    }
}