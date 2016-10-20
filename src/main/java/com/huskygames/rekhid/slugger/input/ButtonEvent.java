package com.huskygames.rekhid.slugger.input;

import com.huskygames.rekhid.slugger.actor.Fighter;

public class ButtonEvent {
    private final ButtonType button;
    private final long nanoTime;
    private final Fighter fighter;

    public ButtonEvent(ButtonType button, Fighter fighter, long nanoTime) {
        this.button = button;
        this.fighter = fighter;
        this.nanoTime = nanoTime;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public ButtonType getButton() {
        return button;
    }

    public long getTime() {
        return nanoTime;
    }
}
