package com.huskygames.rekhid.slugger.physics;


import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.Positionable;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.CollisonChecker;
import com.huskygames.rekhid.slugger.util.collison.shape.Circle;
import com.huskygames.rekhid.slugger.util.collison.shape.Rectangle;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;
import com.huskygames.rekhid.slugger.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class PhysicsObject implements Collidable, Positionable {

    protected DoublePair position;
    protected DoublePair velocity;

    protected boolean gravity;
    private static final Logger logger = LogManager.getLogger(PhysicsObject.class.getName());

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public DoublePair getVelocity() {
        return velocity;
    }

    public void setVelocity(DoublePair velocity) {
        this.velocity = velocity;
    }

    @Override
    public DoublePair getPosition() {
        return position;
    }

    public void setPosition(DoublePair position) {
        this.position = position;
    }

    public void update(World world) {
        if (gravity) {
            boolean hitLevel = false;
            for (Shape shape: world.getLevel().getCollisions()) {
                for (Shape actorBox: getCollisions()) {
                    // TODO: Fix this, it's garbage

                    if (shape instanceof Rectangle) {
                        if (actorBox instanceof Circle) {
                            Circle cir = (Circle) actorBox;
                            Rectangle rectangle = (Rectangle) shape;
                                if (cir.getPosition().getY() + cir.getRadius() >= rectangle.getMin().getY()) {
                                    if (cir.getPosition().getY() + cir.getRadius() <= rectangle.getMax().getY()) {
                                        if (cir.getPosition().getX() + cir.getRadius() >= rectangle.getMin().getX()) {
                                            if (cir.getPosition().getX() + cir.getRadius() <= rectangle.getMax().getX()) {
                                                hitLevel = true;
                                                if (Rekhid.getInstance().getTickCount() % 60 == 0 && Definitions.NOISY_COLLIDER) {
                                                    logger.info("Circle at: " + cir.getPosition() + " with radius: " + cir.getRadius() +
                                                            " collided with rect: " + rectangle);
                                                }
                                                if (velocity.getY() >= 0) {
                                                    velocity.setY(0);
                                                    decollide(rectangle, cir);
                                                }
                                            }
                                        }
                                    }
                                }
                        }

                    }
//                    if (CollisonChecker.intersects(shape, actorBox)) {
//                        hitLevel = true;
//                    }
                }
            }
            if (!hitLevel) {
                velocity.addInPlace(Definitions.GRAVITY);
            }
        }

        position.addInPlace(velocity);
    }

    private void decollide(Rectangle shape, Circle cir) {
        double mod = cir.getPosition().getY() + cir.getRadius() - shape.getMin().getY();
        this.position.setY(position.getY() - mod);
    }
}
