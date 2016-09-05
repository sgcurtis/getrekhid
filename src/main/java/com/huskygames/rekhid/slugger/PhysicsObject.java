package com.huskygames.rekhid.slugger;


public abstract class PhysicsObject {

    private DoublePair position;
    private DoublePair velocity;

    private boolean gravity;

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

    public DoublePair getPosition() {
        return position;
    }

    public void setPosition(DoublePair position) {
        this.position = position;
    }

    public void update() {
        if (gravity) {
            velocity.add(PhysicsManager.GRAVITY);
        }

        position.add(velocity);
    }
}
