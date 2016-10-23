package com.huskygames.rekhid.slugger.resource.sprite;

public class SpriteState {

    public SpriteSequence getSequence() {
        return sequence;
    }

    private final SpriteSequence sequence;

    private int cur = 0;
    private int temp = 0;
    private boolean loop;

    public SpriteState(SpriteSequence sequence, boolean loop, int start) {
        this.sequence = sequence;
        this.loop = loop;
        this.cur = start;
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
            if (cur + 2 == sequence.row.length && !loop) {
                // end of animation settings
                assert true;
            }
            else {
                cur = (cur + 1) % sequence.row.length;
            }
        }
    }
}
