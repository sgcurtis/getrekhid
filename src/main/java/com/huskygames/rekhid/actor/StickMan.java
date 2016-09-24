package com.huskygames.rekhid.actor;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.actor.Actor;
import com.huskygames.rekhid.slugger.actor.ActorCircle;
import com.huskygames.rekhid.slugger.actor.Fighter;
import com.huskygames.rekhid.slugger.actor.Player;
import com.huskygames.rekhid.slugger.input.ButtonEvent;
import com.huskygames.rekhid.slugger.input.ControllerInput;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.resource.sprite.SpriteSheet;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;
import com.huskygames.rekhid.slugger.world.Heightable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.Set;

import java.util.*;


public class StickMan extends Player {
    private final Professor prof;
    private final SpriteSheet sprite;
    private ControllerInput input;
    private Set<Shape> colliders = new HashSet<>();
    private double speed = 5;

    private long tickcount = 0;
    private Logger logger = LogManager.getLogger(StickMan.class.getName());

    public StickMan(DoublePair pos, DoublePair vel, Professor prof) {
        super(pos, vel);
        this.prof = prof;

        this.lifetime = Actor.FOREVER;

        this.sprite = (SpriteSheet) Rekhid.getInstance().getResourceManager().requestResource(Resource.STICK_MAN);
        this.input = Rekhid.getInstance().getControllerManager();

        colliders.add(new ActorCircle(new DoublePair(0, getHeight() / 4), this, getHeight() / 6));
        colliders.add(new ActorCircle(new DoublePair(0, 0), this, getHeight() / 6));
        colliders.add(new ActorCircle(new DoublePair(0, -getHeight() / 4), this, getHeight() / 6));
    }

    private BufferedImage getHead() {
        LoadedImage temp = null;
        switch (prof) {
            case KUHL:
                temp = (LoadedImage) Rekhid.getInstance().getResourceManager()
                        .requestResource(Resource.KUHLS_HEAD);
                break;
            case LEO:
                temp = (LoadedImage) Rekhid.getInstance().getResourceManager()
                        .requestResource(Resource.LEOS_HEAD);
        }
        return temp.getImage();
    }

    @Override
    public void tick() {
        //position.addInPlace(1.5, 0);
        tickcount++;
        if (executing == 0) {

            Queue<ButtonEvent> buttonEvents = input.consumeEventsForPlayer(this);
            if (buttonEvents != null) {
                if (buttonEvents.peek() != null) {
                    switch (buttonEvents.poll().getButton()) {
                        case ATTACK_BUTTON:
                            break;
                        case SPECIAL_BUTTON:

                            break;
                        case JUMP_BUTTON:
                            velocity.setY(-1);
                            position.setY(position.getY() - 3);
                            break;
                        case SHIELD_BUTTON:
                            break;
                        case TAUNT_BUTTON:
                            break;
                        case START_BUTTON:
                            break;
                        default:

                            break;
                    }
                }
                getMovement();
            }
        } else {
            executing--;
        }
    }

    @Override
    public BufferedImage getSprite() {
        return sprite.getSprite(((int) tickcount / 240) % 12, 1, tickcount % 120 > 60);
    }

    @Override
    public double getAspectRatio() {
        return getSprite().getWidth() / (double) getSprite().getHeight();
    }

    private int getPrimaryDirection(DoublePair input) {
        int temp = 4;
        //down = 0, left = 1, up = 2, right = 3 neutral = 4
        if (Math.abs(input.getX()) - Math.abs(input.getY()) > Definitions.DEADZONE) {
            if (input.getX() < -Definitions.DEADZONE)
                temp = 1;
            else if (input.getX() > Definitions.DEADZONE)
                temp = 3;
        } else if (Math.abs(input.getY()) - Math.abs(input.getX()) > Definitions.DEADZONE) {
            if (input.getY() < -Definitions.DEADZONE)
                temp = 2;
            else if (input.getY() > Definitions.DEADZONE)
                temp = 0;
        } else {
            temp = 4;
        }
        return temp;
    }

    private void getMovement() {
        DoublePair temp = input.getStickForPlayer(this);
        if (temp != null) {
            if (temp.getX() < -Definitions.DEADZONE) //first is left or right, -1 to 1
                moveLeft();
            else if (temp.getX() > Definitions.DEADZONE)
                moveRight();
            else
                velocity.addInPlace(new DoublePair(-velocity.getX() / 2, 0));

        }
    }

    private void moveRight() {
        if (getVelocity().getX() < Definitions.MAXVPOS) {
            if (getVelocity().getX() + speed > Definitions.MAXVPOS) {
                velocity.addInPlace(new DoublePair(Definitions.MAXVPOS - speed, 0));
            } else {
                velocity.addInPlace(new DoublePair(speed, 0));
            }
        }

    }

    private void moveLeft() {
        if (getVelocity().getX() > Definitions.MAXVNEG) {
            if (getVelocity().getX() - speed < Definitions.MAXVNEG) {
                velocity.addInPlace(new DoublePair(Definitions.MAXVNEG + speed, 0));
            } else {
                velocity.addInPlace(new DoublePair(-speed, 0));
            }
        }
    }

    private void moveUp() {

    }

    private void moveDown() {

    }

    @Override
    public Set<Shape> getCollisions() {
        return colliders;
    }

    private void attack() {
        switch (getPrimaryDirection(input.getStickForPlayer(this))) {
            case 0: // down
                break;
            case 1: // left
                break;
            case 2: // up
                break;
            case 3: // right
                break;
            case 4: // neutral
                break;
        }
    }

    @Override
    public int getHeight() {
        return Definitions.DEFAULT_PLAYER_HEIGHT;
    }

    @Override
    public String getName() {
        if (prof == Professor.KUHL) {
            return "DR. KUHL";
        }
        else if (prof == Professor.LEO) {
            return "LEO";
        }
        else {
            return "UNKNOWN PROFESSOR";
        }
    }
}
