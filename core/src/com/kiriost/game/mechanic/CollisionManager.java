package com.kiriost.game.mechanic;

import com.kiriost.game.gameobject.WorldManager;
import com.kiriost.game.gameobject.character.ICollider;

import java.util.Iterator;

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

    public boolean collide(ICollider character) {
        Iterator<? extends ICollider> gameObjects = WorldManager.getInstance().playerIterator();
        ICollider gameObject;
        while (gameObjects.hasNext()) {
            gameObject = gameObjects.next();
            if (character == gameObject)
                continue;
            if (character.collide(gameObject)) {
                return true;
            }
        }
        gameObjects = WorldManager.getInstance().npcIterator();
        while (gameObjects.hasNext()) {
            gameObject = gameObjects.next();
            if (character.collide(gameObject)) {
                return true;
            }
        }
        return false;
    }
}
