package com.huskygames.rekhid.slugger.input;

import com.huskygames.rekhid.slugger.actor.Player;

public class ButtonEvent {
    private final ButtonType button;
    private final long nanoTime;

    public Player getPlayer() {
        return player;
    }

    public ButtonType getButton() {
        return button;
    }

    private final Player player;

    public ButtonEvent(ButtonType button, Player player, long nanoTime) {
        this.button = button;
        this.player = player;
        this.nanoTime = nanoTime;
    }
}
