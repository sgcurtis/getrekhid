package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.physics.PhysicsObject;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.*;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;
import com.huskygames.rekhid.slugger.world.Heightable;
import com.huskygames.rekhid.slugger.world.ViewPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public abstract class Actor extends PhysicsObject implements Heightable {
    private final static Logger logger = LogManager.getLogger(Actor.class);
    protected int lifetime;
    protected Set<Shape> hurters = new HashSet<>();

    public static final int FOREVER = -1;

    public Actor() {
        this(new DoublePair(0,0), new DoublePair(0,0));
    }

    public Actor(DoublePair pos, DoublePair vel) {
        this.gravity = true;
        this.velocity = vel;
        this.position = pos;
    }

    public abstract void tick();

    public abstract BufferedImage getSprite();

    /**
     * Gets the ratio width/height of the sprite
     */
    public abstract double getAspectRatio();

    public int getLifetime() {
        return lifetime;
    }

    public Set<Shape> getPain() { return hurters;}

    public DoublePair getRelativePosition(ViewPort port) {
        return getPosition().subtract(port.getTopLeft());
    }
}
