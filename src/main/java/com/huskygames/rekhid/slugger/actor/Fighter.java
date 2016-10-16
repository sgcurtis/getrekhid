package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.actor.Actor;
import com.huskygames.rekhid.slugger.util.DoublePair;

public abstract class Fighter extends Actor {

    protected double damage;
    protected boolean executing;
    protected boolean disabled;
    protected boolean facingLeft;

    public Fighter(DoublePair pos, DoublePair vel) {
        super(pos, vel);
        damage = 4.00;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void addDamage(double amount) {
        this.damage += amount;
    }

    public void healDamage(double amount) {
        this.damage -= amount;
    }

    public abstract String getName();

    public void takeDamage(HurtBox hit){
        damage -= 0.10;
        velocity.addInPlace(hit.getEffect());
    }
}
