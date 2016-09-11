package com.huskygames.rekhid.slugger.input;

import com.huskygames.rekhid.slugger.Player;

public class ButtonEvent {
    private final ButtonType button;

    public Player getPlayer() {
        return player;
    }

    public ButtonType getButton() {
        return button;
    }

    private final Player player;

    public ButtonEvent(ButtonType button, Player player) {
        this.button = button;
        this.player = player;
    }
}
