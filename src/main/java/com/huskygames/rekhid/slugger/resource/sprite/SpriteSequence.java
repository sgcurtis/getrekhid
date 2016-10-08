package com.huskygames.rekhid.slugger.resource.sprite;

public class SpriteSequence {
    private int[] row;
    private int[] col;
    private int[] frames;
    private int cur;
    private int temp = 0;
    private boolean loop;
    private String animation;

    public SpriteSequence(int[] rowseq, int[] colseq, int[] frames, String anim, boolean loop){
        row = rowseq;
        col = colseq;
        this.frames = frames;
        cur = 0;
        this.loop = loop;
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
        if(frames[cur]-temp > 0){
            temp++;
        } else{
            temp = 0;
            if ( cur + 2 == row.length && !loop )
                assert true;
            else
                cur = (cur + 1) % row.length;
        }
    }
}
