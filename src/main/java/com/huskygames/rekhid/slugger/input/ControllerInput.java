package com.huskygames.rekhid.slugger.input;

import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.actor.Player;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ControllerInput {

    private final Rekhid parent;

    private HashMap<Controller, Player> controls = new HashMap<>();

    private HashMap<Player, Queue<ButtonEvent>> queues = new HashMap<>();

    public ControllerInput(Rekhid parent) {
        this.parent = parent;
    }

    public void tick() {
        for (Map.Entry<Controller, Player> controller : controls.entrySet()) {
            // TODO: read this
        }
    }

    /**
     * Returns a list of controllers that are currently plugged in and valid for playing the game
     */
    public List<Controller> getValidControllers() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        return Arrays.stream(controllers).filter((controller) -> {
                    boolean isGood = false;
                    Controller.Type type = controller.getType();

                    if (type == Controller.Type.GAMEPAD) {
                        isGood = true;
                    }
                    if (type == Controller.Type.FINGERSTICK) {
                        isGood = true;
                    }
                    if (type == Controller.Type.KEYBOARD) {
                        isGood = true;
                    }
                    return isGood;
                }
        ).collect(Collectors.toList());
    }

    /**
     * Returns a list of events that happened since last consumption,in the order they occurred.
     * Should use "consumeEvents" for consequential actions.
     * <p>
     * DO NOT MODIFY THE RETURNED LIST
     */
    public Queue<ButtonEvent> requestEventsForPlayer(Player p) {
        // TODO: actually return this
        return queues.get(p);
    }

    /**
     * "Consumes" the events so that they're taken off the queue, never to be seen again.
     *
     * @param p
     * @return
     */
    public Queue<ButtonEvent> consumeEventsForPlayer(Player p) {
        // TODO: CONSUME
        return null;
    }

    public DoublePair getStickForPlayer(Player p) {
        // TODO: get their stick position
        return null;
    }
}
