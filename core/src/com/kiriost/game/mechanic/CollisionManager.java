package com.kiriost.game.mechanic;

import com.kiriost.game.gameobject.GameObject;

/**
 * Created by kiriost on 15/04/16.
 */
public class CollisionManager {
    private static CollisionManager instance;

    private CollisionManager() {
    }

    public static CollisionManager getInstance() {
        if (instance == null) {
            instance = new CollisionManager();
        }
        return instance;
    }

    public boolean collide(GameObject character) {
        return false;
    }
}
