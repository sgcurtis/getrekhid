package com.huskygames.rekhid.slugger.resource.sprite;

public class SpriteSequence {
    private int[] row;
    private int[] col;
    private int[] frames;
    private int cur;
    private String animation;

    public SpriteSequence(int[] rowseq, int[] colseq, int[] frames, String anim){
        row = rowseq;
        col = colseq;
        this.frames = frames;
        cur = 0;
        animation = anim;
    }
    public int getX(){
        return row[cur];
    }
    public int getY(){
        return col[cur];
    }
    public int getFrames(){
        return frames[cur];
    }
    public String getAnimation(){
        return animation;
    }
    public void next(){
        if(frames[cur] > 0){
            frames[cur]--;
        } else{
            cur = (cur + 1) % row.length;
        }
    }
}
