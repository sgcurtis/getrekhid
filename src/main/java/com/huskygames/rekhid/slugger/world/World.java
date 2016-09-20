package com.huskygames.rekhid.slugger.world;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.slugger.Positionable;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.actor.StickMan;
import com.huskygames.rekhid.slugger.Drawable;
import com.huskygames.rekhid.slugger.actor.Actor;
import com.huskygames.rekhid.slugger.actor.ActorCircle;
import com.huskygames.rekhid.slugger.actor.Player;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.IntPair;
import com.huskygames.rekhid.slugger.util.SpriteUtilities;
import com.huskygames.rekhid.slugger.util.collison.shape.Circle;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.AffineTransformOp;


/**
 * The world object, contains the grid, and all the objects on the grid.
 */
public class World implements Drawable {

    //holds the height, in case it's not default
    //width is known no matter what, as aspect ratio is kept at 16:9
    private int height = Definitions.DEFAULT_WORLD_SIZE.getX();

    //private Grid grid;
    private StickMan[] players;

    //level contains its own colliders
    private Level level;

    private ViewPort viewPort;
    private Logger logger = LogManager.getLogger(World.class.getName());

    /**
     * Constructs the world object with default height and width
     *
     * @param level: The Level that will be running on this world
     */
    public World(Level level, int viewHeight, int viewTLX, int viewTLY, StickMan player) {
        //grid = new Grid(Definitions.DEFAULT_WIDTH, Definitions.DEFAULT_HEIGHT);
        players = new StickMan[4];
        for (int i = 0; i < 4; i++) {
            players[i] = null;
        }
        players[0] = player;

        //defined level
        this.level = level;

        //starting viewport
        viewPort = new ViewPort(viewHeight, new IntPair(viewTLX, viewTLY), height);
    }

    /**
     * Constructs the world using abnormal height
     *
     * @param height:   the custom height used in this world
     * @param theLevel: the level that will run on this world
     */
    public World(int height, Level theLevel, int viewHeight, int viewTLX, int viewTLY, StickMan player) {
        //grid = new Grid(width, height);
        this.height = height;

        players = new StickMan[4];
        for (int i = 0; i < 4; i++) {
            players[i] = null;
        }
        players[0] = player;
        level = theLevel;

        viewPort = new ViewPort(viewHeight, new IntPair(viewTLX, viewTLY), height);
    }


    /**
     * Returns the scale of the view, based on the height of the viewport and the height of the world
     *
     * @return: a double of the scale of the viewport to the world
     * NOTE: the scale should never be less than 1. This is strictly to blow up the image when the viewport is zoomed in.
     * The largest the viewport can be is the size of the world itself.
     */
    public double scaleView() {
        return height / (double) viewPort.getHeight();
    }

    /**
     * This is the ratio between grid units and pixels
     */
    public double getViewRatio() {
        return viewPort.getHeight() / (double) viewPort.getWindowSize().getY();
    }

    /**
     * Returns the position of the item in the frame, in grid units
     */
    private DoublePair translatePosition(Positionable pos) {
        return translatePositionPair(pos.getPosition());
    }

    private DoublePair translatePositionPair(DoublePair pair) {
        return pair.subtract(viewPort.getTopLeft());
    }
    /**
     * Returns a number from that represents how large it is, relative to the viewport
     * (must be the height).
     * for example: if the view port is 500, and 100 is passed in, it will be 0.2
     */
    private double percentOfHeight(int gridSize) {
        return ((double) gridSize) / viewPort.getHeight();
    }

    private int getPixelHeight(int gridSize) {
        return (int) ((percentOfHeight(gridSize) * gridSize * scaleView()));
    }

    private int centrer(double pos, double in) {
        return (int) Math.round(pos - in / 2);
    }

    /**
     * Draws a small sphere representing the centre of an actor, given the position in pixels
     */
    private void drawCenter(IntPair position, Graphics2D context) {
        Color temp = context.getColor();
        context.setColor(Definitions.CENTRE_COLOR);


        context.fillOval(position.getX() - 5, position.getY() - 5 , 10, 10);
        context.setColor(temp);
    }

    private void drawActor(Actor actor, Graphics2D context) {
        //context.drawLine(0, 250, 5000, 250);
        //context.drawLine(0, 350, 5000, 350);
        DoublePair centre = translatePosition(actor);
        IntPair pixelCenter = centre.multiply(getViewRatio()).rounded();
        double heightInPix = actor.getHeight() * (1 / getViewRatio());
        double widthInPix = heightInPix * actor.getAspectRatio();

        if (Rekhid.getInstance().getTickCount() % 60 == 0) {
            logger.info("Viewport height, in px: " + viewPort.getWindowSize().getY() + " viewport height, in gridunits: "  + viewPort.getHeight());
            logger.info("Therefore, our gridunit to pixel ratio is: " + getViewRatio());
            logger.info("The actor is at relative grid position of: "
                    + actor.getRelativePosition(viewPort) + " given a grid that starts at " + viewPort.getTopLeft());
            int top = centrer(actor.getPosition().getY(), actor.getHeight());
            logger.info(" Given the actor's height, he will go from " + top + " to " + (top + actor.getHeight()));
            logger.info(" Since the actor is " + actor.getHeight() + "/" + viewPort.getHeight() +
                    " he will be " + getPixelHeight(actor.getHeight()) + " pixels high, or " +
                    actor.getHeight() / (double) viewPort.getHeight()*100 + "%");
        }

        AffineTransformOp scaler = SpriteUtilities.buildScaleOp(
                actor.getSprite().getHeight(null), (int) heightInPix);


        context.drawImage(actor.getSprite(), scaler,
                centrer(pixelCenter.getX(), heightInPix),
                centrer(pixelCenter.getY(), widthInPix));

        if (Definitions.DRAW_CENTRES) {
            drawCenter(pixelCenter, context);
        }

        if (Definitions.DRAW_HITBOXES) {
            drawHitbox(actor, context);
        }
    }

    private void drawHitbox(Actor actor, Graphics2D context) {
        Color temp = context.getColor();
        context.setColor(Definitions.HITBOX_COLOR);
        for (Shape shape : actor.getCollisions()) {
            DoublePair position = translatePosition(shape);

            DoublePair pixelCenter = position.multiply(getViewRatio());


            if (shape instanceof Circle) {
                int radiusInPx = (int) (((Circle) shape).getRadius() * (1 / getViewRatio()));
                int diamInPx = radiusInPx * 2;
                context.fillOval(centrer(pixelCenter.getX(), diamInPx),
                        centrer(pixelCenter.getY(), diamInPx), diamInPx, diamInPx);
                //context.fillOval((int)pixelCenter.getY(), (int) pixelCenter.getX(), 2, 2);
                ActorCircle cir = (ActorCircle) shape;
                if (Rekhid.getInstance().getTickCount() % 60 == 0) {
                    logger.info("   Actor is at: " + actor.getPosition() + " hitbox is at offset " +
                            cir.getOffset()+ ", therefore the computed position is " + cir.getPosition());
                    logger.info("   my radius in pixels is: " + radiusInPx);

                }
            }
        }
        context.setColor(temp);
    }

    @Override
    public void draw(Graphics2D context) {
        for (Player ply : players) {
            if (ply != null) {
                drawActor(ply, context);
            }
        }
    }

    public void tick() {
        for (Player ply : players) {
            if (ply != null) {
                ply.tick();
            }
        }


    }
}
