package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.actor.Actor;
import com.huskygames.rekhid.slugger.util.DoublePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public abstract class Fighter extends Actor {

    private final static Logger logger = LogManager.getLogger(Fighter.class);
    protected double damage;
    protected boolean executing;
    protected boolean disabled;
    protected boolean facingLeft;
    protected Set<Fighter> fightersHit = new HashSet<>();

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
        if(hit.getParent().equals(this))
            return;

        if(((Fighter)hit.getParent()).getDamaged().contains(this))
            return;

        logger.info(null, "Damaging Player");
        ((Fighter)hit.getParent()).getDamaged().add(this);
        damage -= ((double)hit.getDamage()) * 0.01;
        velocity.addInPlace(hit.getEffect());
    }

    public Set<Fighter> getDamaged() { return fightersHit; }
    public void clearDamaged(){
        fightersHit.clear();
    }
}
