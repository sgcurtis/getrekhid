package com.huskygames.rekhid.slugger.resource.sprite;

import com.huskygames.rekhid.slugger.actor.Fighter;

public class SpriteState {

    public SpriteSequence getSequence() {
        return sequence;
    }

    private final SpriteSequence sequence;

    private int cur = 0;
    private int temp = 0;
    private boolean loop;
    private Fighter parent;

    public SpriteState(SpriteSequence sequence, boolean loop, int start, Fighter parent) {
        this.sequence = sequence;
        this.loop = loop;
        this.cur = start;
        this.parent = parent;
    }


    public int getX() {
        return sequence.row[cur];
    }

    public int getY() {
        return sequence.col[cur];
    }

    public int getFrames() {
        return sequence.frames[cur];
    }

    public void next() {
        if (sequence.frames[cur] - temp > 0) {
            temp++;
        } else {
            temp = 0;
            if (cur+1 == sequence.row.length && !loop) {
                parent.endAnimation();
            }
            else {
                cur = (cur + 1) % sequence.row.length;
            }
        }
    }
}
