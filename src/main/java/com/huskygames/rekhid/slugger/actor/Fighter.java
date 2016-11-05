package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.util.DoublePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public abstract class Fighter extends Actor {

    private final static Logger logger = LogManager.getLogger(Fighter.class);
    private int lives = 3;
    protected double damage;
    protected boolean executing;
    protected boolean dead;
    protected boolean disabled;
    protected boolean facingLeft;
    /*
        While this fighter is attacking, this set keeps track of the other Fighters that have been
        hit already, so that they aren't repeatedly hit each tick by the same attack
        TODO: refactor this down to an "attack" class
     */
    protected Set<Fighter> fightersHit = new HashSet<>();

    public Fighter(DoublePair pos, DoublePair vel) {
        super(pos, vel);
        damage = 4.00;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
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

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void removeLife() {
        this.lives--;
        logger.warn("Lives remaining: " + lives);
    }

    public int getLives() {
        return lives;
    }

    public abstract String getName();

    public void takeDamage(HurtBox hit) {
        // double check we're not hurting ourselves
        if (hit.getParent().equals(this)) {
            return;
        }

        if (hit.getParent() instanceof Fighter) {
            Fighter parent = (Fighter) hit.getParent();
            // if we've already been hit by this attacker, don't hurt us
            if (parent.getDamaged().contains(this)) {
                return;
            }
            parent.getDamaged().add(this);
            damage -= hit.getDamage() * 0.01;
            velocity.addInPlace(hit.getLaunchVector());
        }
        else {
            throw new IllegalArgumentException("Non Fighter attacks not implemented.");
        }
    }

    public Set<Fighter> getDamaged() {
        return fightersHit;
    }

    public void clearDamaged() {
        fightersHit.clear();
    }
}
