package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.awt.image.BufferedImage;
import java.util.HashSet;

public class DefaultLevel extends Level {
    public DefaultLevel() {
        background = Resource.DEFAULT_LEVEL_BG;
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
}
