package com.huskygames.rekhid.slugger.physics;

import com.huskygames.rekhid.slugger.Drawable;
import com.huskygames.rekhid.slugger.world.World;

public abstract class Particle extends PhysicsObject implements Drawable {

    // determines how quickly, if at all, this fades.
    protected double fade;
    // value changed by fading, if alpha reaches zero, this particle is obsoleted
    protected double alpha;
    // whether or not this particle is ready to be removed
    protected boolean obsoleted;
    // how long this particle lasts, in ticks
    protected long lifespan;

    public double getFade() {
        return fade;
    }

    public void setFade(double fade) {
        this.fade = fade;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public boolean isObsoleted() {
        return obsoleted;
    }

    public void setObsoleted(boolean obsoleted) {
        this.obsoleted = obsoleted;
    }

    public long getLifespan() {
        return lifespan;
    }

    public void setLifespan(long lifespan) {
        this.lifespan = lifespan;
    }

    @Override
    public void update(World world) {
        super.update(world);

        alpha -= fade;
        lifespan--;
    }
}
