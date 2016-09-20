package com.huskygames.rekhid.slugger.input;

import net.java.games.input.Component;

import java.io.InputStream;
import java.util.HashMap;

public abstract class ControlMapper {
    public abstract ButtonType translate(Component.Identifier iden);
}
