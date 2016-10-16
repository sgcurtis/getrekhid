package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.util.DoublePair;

public class HurtBox extends ActorCircle {
    private DoublePair effect;
    private Actor parent;
    private int damage;
    private int lifetime;

    public HurtBox(DoublePair offset, Actor parent, double r, DoublePair hit, int damage, int life){
        super(offset, parent, r);
        this.parent = parent;
        effect = hit;
        this.damage = damage;
        lifetime = life;
    }

    public DoublePair getEffect() {
        return effect;
    }
    public Actor getParent() { return parent; }
    public boolean decrementLife(){
        return --lifetime <= 0;
    }
}
