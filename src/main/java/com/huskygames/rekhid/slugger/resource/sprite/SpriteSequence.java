package com.huskygames.rekhid.slugger.resource.sprite;

public class SpriteSequence {
    int[] row;
    int[] col;
    int[] frames;
    String name;

    public SpriteSequence(int[] rowseq, int[] colseq, int[] frames, String name) {
        row = rowseq;
        col = colseq;
        this.frames = frames;
        this.name = name;
    }


    public String getName() {
        return name;
    }


}
