package com.huskygames.rekhid.slugger.physics;


import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.slugger.Positionable;
import com.huskygames.rekhid.slugger.util.DoublePair;

public abstract class PhysicsObject implements Collidable, Positionable {

    protected DoublePair position;
    protected DoublePair velocity;

    protected boolean gravity;

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public DoublePair getVelocity() {
        return velocity;
    }

    public void setVelocity(DoublePair velocity) {
        this.velocity = velocity;
    }

    @Override
    public DoublePair getPosition() {
        return position;
    }

    public void setPosition(DoublePair position) {
        this.position = position;
    }

    public void update() {
        if (gravity) {
            velocity.addInPlace(Definitions.GRAVITY);
        }

        position.addInPlace(velocity);
    }
}
