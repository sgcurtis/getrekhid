package com.huskygames.rekhid.actor;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.actor.*;
import com.huskygames.rekhid.slugger.input.ButtonEvent;
import com.huskygames.rekhid.slugger.input.ControllerInput;
import com.huskygames.rekhid.slugger.resource.LoadedImage;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.resource.sprite.SpriteSequence;
import com.huskygames.rekhid.slugger.resource.sprite.SpriteSheet;
import com.huskygames.rekhid.slugger.resource.sprite.SpriteState;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

/**
 * A simple StickMan intended to illustrate the game
 */
public class StickMan extends Fighter {
    private final Professor prof;
    private final SpriteSheet sprite;
    private SpriteState sequence;
    private ControllerInput input;
    private Set<Shape> colliders = new HashSet<>();
    private double speed = 0.5;
    private double slidiness = 10;
    private int jumps = 2;
    private DoublePair playerPos;

    // declare sprite sequences
    SpriteSequence moveRight = new SpriteSequence(
            new int[]{0, 0, 0, 0,  0,  0, 1, 1, 1},
            new int[]{6, 7, 8, 9, 10, 11, 0, 1, 2},
            new int[]{5, 5, 5, 5,  5,  5, 5, 5, 5}, "moveRight");
    SpriteSequence moveLeft = new SpriteSequence(
            new int[]{0, 0, 0, 0,  0,  0, 1, 1, 1},
            new int[]{6, 7, 8, 9, 10, 11, 0, 1, 2},
            new int[]{5, 5, 5, 5,  5,  5, 5, 5, 5}, "moveLeft");
    SpriteSequence jump = new SpriteSequence(
            new int[]{1,  1,  1,  1,  1,  1},
            new int[]{5,  6,  7,  8,  9, 10},
            new int[]{3, 10, 10, 10, 10, 10}, "jumping");
    SpriteSequence neutralAttack = new SpriteSequence(
            new int[]{3,3,3},
            new int[]{4,5,4},
            new int[]{5,5,5}, "neutralAttack");

    // end sprite sequences

    private static final Logger logger = LogManager.getLogger(StickMan.class.getName());

    public StickMan(DoublePair pos, DoublePair vel, Professor prof) {
        super(pos, vel);
        this.prof = prof;
        this.playerPos = pos;

        this.lifetime = Actor.FOREVER;
        executing = false;
        this.sprite = (SpriteSheet) Rekhid.getInstance().getResourceManager()
                .requestResource(Resource.STICK_MAN);
        this.input = Rekhid.getInstance().getControllerManager();

        createDefaultHitbox();
    }

    private void createDefaultHitbox() {
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
        // only work if the player is enabled
        if (!disabled) {
            if (executing) {
                sequence.next();
            }

            updateHurtBoxes();

            readController();
        }
    }

    private void readController() {
        Queue<ButtonEvent> buttonEvents = input.consumeEventsForPlayer(this);
        if (buttonEvents != null) {
            if (buttonEvents.peek() != null) {
                switch (buttonEvents.poll().getButton()) {
                    case ATTACK_BUTTON:
                        attack();
                        break;
                    case SPECIAL_BUTTON:
                        break;
                    case JUMP_BUTTON:
                        jump();
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
            updateMovement();
        }
    }

    protected void updateHurtBoxes() {
        Iterator<Shape> i = hurters.iterator();
        Shape cur;
        while (i.hasNext()) {
            cur = i.next();
            if (cur instanceof HurtBox) {
                if (((HurtBox) cur).decrementLife()) {
                    i.remove();
                }
            }
        }
        if (hurters.isEmpty()) {
            clearDamaged();
        }
    }

    @Override
    public BufferedImage getSprite() {
        if (!executing || sequence == null) {
            return sprite.getSprite(6, 1, facingLeft);
        } else {
            return sprite.getSprite(sequence.getY(), sequence.getX(), facingLeft);
        }
    }

    @Override
    public double getAspectRatio() {
        return getSprite().getWidth() / (double) getSprite().getHeight();
    }

    private int getPrimaryDirection(DoublePair input) {
        int temp = -1;

        if (input == null) {
            return -1;
        }

        // down = 0, left = 1, up = 2, right = 3 neutral = 4 error = -1
        if (Math.abs(input.getX()) - Math.abs(input.getY()) > Definitions.DEADZONE) {
            if (input.getX() < -Definitions.DEADZONE) {
                temp = 1;
            }
            else if (input.getX() > Definitions.DEADZONE) {
                temp = 3;
            }
        } else if (Math.abs(input.getY()) - Math.abs(input.getX()) > Definitions.DEADZONE) {
            if (input.getY() < -Definitions.DEADZONE) {
                temp = 2;
            }
            else if (input.getY() > Definitions.DEADZONE) {
                temp = 0;
            }
        } else {
            temp = 4;
        }
        return temp;
    }

    private void updateMovement() {
        int dir = getPrimaryDirection(input.getStickForPlayer(this));
        if (dir == 1) {
            if (getVelocity().getX() > 0) {
                setVelocity(new DoublePair(-getVelocity().getX(), getVelocity().getY()));
            }

            moveLeft();
        } else if (dir == 3) {
            if (getVelocity().getX() < 0) {
                setVelocity(new DoublePair(-getVelocity().getX(), getVelocity().getY()));
            }

            moveRight();
        } else {
            if (sequence != null && !sequence.getSequence().equals(jump)) {
                executing = false;
            }

            velocity.addInPlace(new DoublePair(-velocity.getX() / slidiness, 0));
        }

        if (jumps != 2 && velocity.getY() == 0) {
            jumps = 2;
        }
    }

    private void moveRight() {
        if (getVelocity().getX() <= Definitions.MAX_VELOCITY) {
            if (getVelocity().getX() + speed > Definitions.MAX_VELOCITY) {
                velocity.addInPlace(new DoublePair(Definitions.MAX_VELOCITY - getVelocity().getX(), 0));
            } else {
                velocity.addInPlace(new DoublePair(speed, 0));
            }
            if (sequence == null || !sequence.getSequence().equals(moveRight)) {
                facingLeft = false;
                sequence = new SpriteState(moveRight, true, 0);
            }
            executing = true;
        }
    }

    //Move up to Player/Fighter?
    private void die() {
        this.dead = true;
    }

    private void moveLeft() {
        if (getVelocity().getX() >= -Definitions.MAX_VELOCITY) {
            if (getVelocity().getX() - speed < -Definitions.MAX_VELOCITY) {
                velocity.addInPlace(new DoublePair(-Definitions.MAX_VELOCITY - getVelocity().getX(), 0));
            } else {
                velocity.addInPlace(new DoublePair(-speed, 0));
            }
            if (sequence == null || !sequence.getSequence().equals(moveLeft)) {
                facingLeft = true;
                sequence = new SpriteState(moveLeft, true, 0);
            }
            executing = true;

        }

    }

    private void jump() {
        if (jumps > 0) {
            position.setY(position.getY() - 3);
            velocity.addInPlace(0, -5);
            jumps--;
            executing = true;
            sequence = new SpriteState(jump, false, 0);
        }
    }

    private void moveDown() {

    }

    @Override
    public Set<Shape> getCollisions() {
        return colliders;
    }

    private void attack() {
        if (hurters.isEmpty()) {
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
                    DoublePair direction;
                    DoublePair offsetLow;
                    DoublePair offsetHigh;
                    if (facingLeft) {
                        direction = new DoublePair(-2, 1);
                        offsetLow = new DoublePair(-10, -15);
                        offsetHigh = new DoublePair(-10, 15);
                    } else {
                        direction = new DoublePair(2, 1);
                        offsetLow = new DoublePair(10, -15);
                        offsetHigh = new DoublePair(10, 15);
                    }
                    hurters.add(new HurtBox(offsetLow, this, 20, direction, 5, 5));
                    hurters.add(new HurtBox(offsetHigh, this, 20, direction, 5, 5));
                    sequence = new SpriteState(neutralAttack, false, 0);
                    executing = true;
                    break;
            }
        } else {
            logger.warn("Cannot attack right now, attack is already in progress");
        }
    }

    public Set<Shape> getPain() {
        return hurters;
    }

    @Override
    public int getHeight() {
        return Definitions.DEFAULT_PLAYER_HEIGHT;
    }

    @Override
    public String getName() {
        if (prof == Professor.KUHL) {
            return "DR. KUHL";
        } else if (prof == Professor.LEO) {
            return "LEO";
        } else {
            return "UNKNOWN PROFESSOR";
        }
    }
}
