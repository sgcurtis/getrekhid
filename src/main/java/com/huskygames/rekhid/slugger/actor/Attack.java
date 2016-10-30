package com.huskygames.rekhid.slugger.actor;

import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Attack {
    private DoublePair[][] offsets;
    private int[][] damages;
    private DoublePair[][] effects;
    private int[] ticks;
    private Actor parent;
    private int temp;
    private int cur;
    private boolean finished = false;

    protected Set<Shape> hurters = new HashSet<>();

    public Attack(DoublePair[][] boxes, int[] stages, int[][] damage, DoublePair[][] launch, Actor parent){
        offsets = boxes;
        ticks = stages;
        damages = damage;
        effects = launch;
        this.parent = parent;
        cur = 0;
        temp = 0;
        genBoxes(cur);
    }

    public boolean next(){
        Iterator<Shape> i = hurters.iterator();
        Shape current;
        while (i.hasNext()) {
            current = i.next();
            if (current instanceof HurtBox) {
                if (((HurtBox) current).decrementLife()) {
                    i.remove();
                }
            }
        }
        if(!finished) {
            if (ticks[cur] - temp > 0) {
                temp++;
            } else {
                temp = 0;
                cur++;
                if (cur == ticks.length) {
                    finished = true;
                } else {
                    genBoxes(cur);
                }
            }
        }
        return finished;
    }

    public Actor getParent(){
        return parent;
    }

    public void genBoxes(int index){
        for(int i = 0; i < offsets[cur].length; i++){
            if(offsets[cur][i] != null) {
                hurters.add(new HurtBox(offsets[cur][i], parent, this, 20, effects[cur][i], damages[cur][i], ticks[i]));
            }
        }
    }

    public Set<Shape> getPain(){
        return hurters;
    }
}
