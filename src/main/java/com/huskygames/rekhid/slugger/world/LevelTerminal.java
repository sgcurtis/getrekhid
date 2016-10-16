package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class LevelTerminal extends Level {

    private final IntPair minView = new IntPair(500, 100);
    private final IntPair maxView = new IntPair(3000, 1500);
    private final int minViewHeight = 300;
    protected IntPair levelSize;
    protected Resource background;
    protected Set<Shape> colliders;
    protected Resource music;
    protected IntPair[] startPos;
    protected ViewPort defaultViewPort;

    public LevelTerminal() {
        background = Resource.LEVEL_TERMINAL_BG;
        levelSize = Definitions.DEFAULT_WORLD_SIZE;
        defaultViewPort = new ViewPort(1500, new IntPair(700, 200), levelSize.getY());
        colliders = new HashSet<Shape>();
        colliders.add(new WorldRectangle(0,500, 7000,1000));
        colliders.add(new WorldRectangle(850,300, 1300,400));
        colliders.add(new WorldRectangle(1890,280, 2300,350));
        colliders.add(new WorldRectangle(3000,300, 3400,400));

    }

    @Override
    public IntPair getLevelSize() { return levelSize; }

    @Override
    public ViewPort getDefaultViewPort() { return defaultViewPort; }

    @Override
    public Resource getBackground(){ return background; }

    @Override
    public BufferedImage getBackgroundImage() {
        return ((LoadedImage) Rekhid.getInstance().getResourceManager()
                .requestResource(background)).getImage();
    }

    @Override
    public Set<Shape> getCollisions() { return colliders; }

    @Override
    public void setColliders(Set<Shape> cols) { colliders = cols; }

    @Override
    public Resource getMusic() { return music; }

    @Override
    public IntPair[] getStartPos() { return startPos; }

    @Override
    public IntPair getMinViewPort() { return minView; }

    @Override
    public IntPair getMaxViewPort() {
        return maxView;
    }

    @Override
    public int getMinViewHeight() {
        return 100;
    }
}
