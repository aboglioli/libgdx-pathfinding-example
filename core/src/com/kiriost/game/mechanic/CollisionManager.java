package com.kiriost.game.mechanic;

import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.WorldManager;

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

    public boolean collide(GameObject character) {
        GameObject gameObject;
        Iterator<? extends GameObject> gameObjects = WorldManager.getInstance().playerIterator();

        while (gameObjects.hasNext()) {
            gameObject = gameObjects.next();
            if (character == gameObject)
                continue;
            if (check(character, gameObject)) {
                return true;
            }
        }

        gameObjects = WorldManager.getInstance().npcIterator();

        while (gameObjects.hasNext()) {
            gameObject = gameObjects.next();
            if (check(character, gameObject)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(GameObject character, GameObject gameObject) {
        return character.getLimit().overlaps(gameObject.getLimit());
    }
}
