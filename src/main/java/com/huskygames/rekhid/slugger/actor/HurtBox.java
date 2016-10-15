package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.util.DoublePair;

public class HurtBox extends ActorCircle {
    private DoublePair effect;
    private int damage;
    private int lifetime;

    public HurtBox(DoublePair offset, Actor parent, double r, DoublePair hit, int damage){
        super(offset, parent, r);
        effect = hit;
        this.damage = damage;
    }

    public DoublePair getEffect() {
        return effect;
    }
}
