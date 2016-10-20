package com.huskygames.rekhid.slugger.input;

/**
 * Created by alex on 10/15/2016.
 */

import net.java.games.input.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;


public class GamepadTranslator extends ControlMapper {
    private static final Logger logger = LogManager.getLogger(GamepadTranslator.class.getName());

    private static final HashMap<Component.Identifier, ButtonType> MAP = new HashMap<>();

    static {
        MAP.put(Component.Identifier.Button._0, ButtonType.ATTACK_BUTTON);
        MAP.put(Component.Identifier.Button._1, ButtonType.SPECIAL_BUTTON);
        MAP.put(Component.Identifier.Button._2, ButtonType.JUMP_BUTTON);
        MAP.put(Component.Identifier.Button._3, ButtonType.JUMP_BUTTON);
        MAP.put(Component.Identifier.Button._4, ButtonType.SHIELD_BUTTON);
        MAP.put(Component.Identifier.Button._5, ButtonType.SHIELD_BUTTON);
        MAP.put(Component.Identifier.Button._6, ButtonType.START_BUTTON);
        MAP.put(Component.Identifier.Button._7, ButtonType.START_BUTTON);


    }

    @Override
    public ButtonType translate(Component.Identifier iden) {
        return MAP.get(iden);
    }

    public Component.Identifier getLeftStickX() {
        return Component.Identifier.Axis.X;
    }

    public Component.Identifier getLeftStickY() {
        return Component.Identifier.Axis.Y;
    }
}
