package com.huskygames.rekhid.actor;

import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.actor.Fighter;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.resource.sprite.SpriteSheet;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.awt.*;
import java.util.Set;

public class StickMan extends Fighter {
    private final Professor prof;
    private final SpriteSheet sprite;

    public StickMan(DoublePair pos, DoublePair vel, Professor prof) {
        super(pos, vel);
        this.prof = prof;

        this.sprite = (SpriteSheet) Rekhid.getInstance().getResourceManager().requestResource(Resource.STICK_MAN);
    }

    private Image getHead() {
        LoadedImage temp = null;
        switch(prof) {
            case KUHL:
                temp = (LoadedImage) Rekhid.getInstance().getResourceManager()
                        .requestResource(Resource.KUHLS_HEAD);
                break;
            case LEO:
                temp = (LoadedImage) Rekhid.getInstance().getResourceManager()
                        .requestResource(Resource.LEOS_HEAD);
        }
        return temp.getImage();
    }

    @Override
    public void tick() {

    }

    @Override
    public Set<Shape> getCollisons() {
        return null;
    }
}
