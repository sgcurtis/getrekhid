package com.huskygames.rekhid.slugger.input;

import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.Player;

import java.util.List;

public class ControllerInput {

    /**
     * Returns a list of events that happened since last consumption,in the order they occurred.
     * Should use "consumeEvents" for consequential actions.
     * @param p
     * @return
     */
    public List<ButtonEvent> requestEventsForPlayer(Player p) {
        // TODO: actually return this
        return null;
    }

    /**
     * "Consumes" the events so that they're taken off the queue, never to be seen again.
     * @param p
     * @return
     */
    public List<ButtonEvent> consumeEventsForPlayer(Player p) {
        // TODO: CONSUME
        return null;
    }

    public DoublePair getStickForPlayer(Player p) {
        // TODO: get their stick position
        return null;
    }
}
