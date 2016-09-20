package com.huskygames.rekhid.slugger.physics;

import java.util.HashSet;

public class PhysicsManager {
    private static PhysicsManager instance;

    private final HashSet<PhysicsObject> objects = new HashSet<>();

    public static PhysicsManager getInstance() {
        if (instance == null) {
            instance = new PhysicsManager();
        }
        return instance;
    }

    public void addObject(PhysicsObject object) {
        objects.add(object);
    }

    public void updateObjects() {
        for (PhysicsObject object: objects) {
            object.update();
        }
    }



}
