package com.huskygames.rekhid.slugger.input;

import net.java.games.input.Component;

import java.util.HashMap;

public class KeyboardTranslator extends ControlMapper {

    private static final HashMap<Component.Identifier, ButtonType> MAP = new HashMap<>();

    static {
        MAP.put(Component.Identifier.Key.J, ButtonType.ATTACK_BUTTON);
        MAP.put(Component.Identifier.Key.K, ButtonType.SPECIAL_BUTTON);
        MAP.put(Component.Identifier.Key.H, ButtonType.START_BUTTON);
        MAP.put(Component.Identifier.Key.W, ButtonType.JUMP_BUTTON);
        MAP.put(Component.Identifier.Key.I, ButtonType.SHIELD_BUTTON);
        MAP.put(Component.Identifier.Key.SPACE, ButtonType.START_BUTTON);
    }

    @Override
    public ButtonType translate(Component.Identifier iden) {
        return MAP.get(iden);
    }

    public Component.Identifier getLeft() {
        return Component.Identifier.Key.A;
    }

    public Component.Identifier getRight() {
        return Component.Identifier.Key.D;
    }


}
