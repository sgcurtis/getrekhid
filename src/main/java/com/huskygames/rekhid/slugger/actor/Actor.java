package com.huskygames.rekhid.slugger.actor;


import com.huskygames.rekhid.slugger.physics.PhysicsObject;
import com.huskygames.rekhid.slugger.util.DoublePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Actor extends PhysicsObject {
    private final static Logger logger = LogManager.getLogger(Actor.class);

    public Actor() {
        this(new DoublePair(0,0), new DoublePair(0,0));
    }

    public Actor(DoublePair pos, DoublePair vel) {
        this.gravity = true;
        this.velocity = vel;
        this.position = pos;
    }

    public abstract void tick();
}
