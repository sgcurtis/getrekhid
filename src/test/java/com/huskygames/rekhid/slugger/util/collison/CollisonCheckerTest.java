package com.huskygames.rekhid.slugger.util.collison;

import com.huskygames.rekhid.slugger.actor.ActorCircle;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.huskygames.rekhid.slugger.world.WorldCircle;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollisonCheckerTest {

    @Test
    public void testIntersects() throws Exception {
        assertFalse(CollisionChecker.intersects(
                new WorldCircle(new DoublePair(40, 40), 10),
                new WorldCircle(new DoublePair(100, 100), 50)));
    }
}