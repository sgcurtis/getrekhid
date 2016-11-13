package com.huskygames.rekhid.actor;

import com.huskygames.rekhid.Definitions;
import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.slugger.actor.Actor;
import com.huskygames.rekhid.slugger.actor.ActorCircle;
import com.huskygames.rekhid.slugger.actor.Attack;
import com.huskygames.rekhid.slugger.actor.Fighter;
import com.huskygames.rekhid.slugger.resource.Resource;
import com.huskygames.rekhid.slugger.resource.sprite.SpriteSheet;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.util.collison.shape.Shape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 * Projectile demo class, can be extended and overridden to do different things as need be.
 * Created by Kyle on 11/5/2016.
 */
public class Projectile extends Fighter {

    private Projectiles proj;
    private SpriteSheet sprite;
    private boolean facingleft;
    private Set<Shape> colliders;
    private Fighter parent;
    private int TTL; // ticks to live

    private static final Logger logger = LogManager.getLogger(Projectile.class.getName());

    public Projectile(DoublePair pos, DoublePair vel, Fighter par, Projectiles type, int lifetime){
        super(pos, vel);
        this.proj = type;
        parent = par;

        setGravity(false);
        this.lifetime = lifetime;
        TTL = lifetime;

        this.sprite = (SpriteSheet) Rekhid.getInstance().getResourceManager()
                .requestResource(Resource.TEST_PROJECTILE);

        facingleft = vel.getX() < 0 ? true : false;
        int multiplier = facingleft ? -1 : 1;
        DoublePair [][] offsets = new DoublePair [][]{new DoublePair[]{new DoublePair(0.0, 5.0)}};
        DoublePair [][] directions = new DoublePair [][]{new DoublePair[]{new DoublePair(multiplier * 20, -5)}};
        int [] ticks = new int[]{20};
        int [][] damages = new int[][]{new int[]{30}};
        int [][] areas = new int[][]{new int[]{10}};

        colliders = new HashSet<Shape>();
        colliders.add(new ActorCircle(new DoublePair(0.0, 5.0), this, 6.0));
        theAttack = new Attack(offsets, ticks, damages, directions, areas, this);

        logger.info("I've been created!!");
    }

    public int getHeight() {
        return 10;
    }

    @Override
    public Set<Shape> getCollisions() {
        return colliders;
    }

    @Override
    public void tick() {
        TTL--;
        if(TTL == 0 && parent instanceof Fighter){
            ((Fighter)parent).removeProjectile();
            return;
        }
    }

    @Override
    public BufferedImage getSprite() {
        return sprite.getSprite(0, 0, facingleft);
    }

    @Override
    public double getAspectRatio() {
        return 0;
    }

    public Fighter getParent(){return parent;}

    @Override
    public String getName() {
        return proj.toString();
    }
}
