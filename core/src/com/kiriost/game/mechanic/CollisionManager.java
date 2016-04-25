package com.kiriost.game.mechanic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.gameobject.WorldManager;
import com.kiriost.game.gameobject.character.Character;

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

    public boolean collide(Character character, Array<Movement> path) {
        Character gameObject;
        Iterator<? extends Character> gameObjects = WorldManager.getInstance().playerIterator();

        while (gameObjects.hasNext()) {
            gameObject = gameObjects.next();
            if (character == gameObject)
                continue;
            if (check(character, gameObject)) {
                redirectPath(character, gameObject, path);
                return true;
            }
        }

        gameObjects = WorldManager.getInstance().npcIterator();

        while (gameObjects.hasNext()) {
            gameObject = gameObjects.next();
            if (check(character, gameObject)) {
                redirectPath(character, gameObject, path);
                return true;
            }
        }
        return false;
    }

    private boolean check(Character character, Character gameObject) {
        return character.getLimit().overlaps(gameObject.getLimit());
    }

    private void redirectPath(Character character, Character gameObject, Array<Movement> path) {
        Vector2 vecGameObject = new Vector2(gameObject.getCenterX(), gameObject.getCenterY());
        Vector2 vecDirection = vecGameObject.sub(character.getCenterX(), character.getCenterY());
        float direction = vecDirection.angle();
        Movement movement = path.first();

        float alpha = character.getAlpha();

        boolean xAxis = false, yAxis = false;

        if (direction < alpha) { //right
            xAxis = true;
        } else if (direction < 180 - alpha) { //top
            yAxis = true;
        } else if (direction < 180 + alpha) { //left
            xAxis = true;
        } else if (direction < 360 - alpha) { //bottom
            yAxis = true;
        } else { //right
            xAxis = true;
        }

        Movement newMovement = new Movement();

        if (xAxis) {
            if (movement.getDirection().y > 0) { //up
                newMovement.setDestination(character.getCenterX(), character.getCenterY(),
                        character.getCenterX(),
                        character.getCenterY() + vecDirection.y + gameObject.getOriginY() + character.getOriginY());
            } else { //down
                newMovement.setDestination(character.getCenterX(), character.getCenterY(),
                        character.getCenterX(),
                        character.getCenterY() + vecDirection.y - (gameObject.getOriginY() + character.getOriginY()));
            }
        } else if (yAxis) {
            if (movement.getDirection().x > 0) { //right
                newMovement.setDestination(character.getCenterX(), character.getCenterY(),
                        character.getCenterX() + vecDirection.x + gameObject.getOriginX() + character.getOriginY(),
                        character.getCenterY());
            } else { //left
                newMovement.setDestination(character.getCenterX(), character.getCenterY(),
                        character.getCenterX() + vecDirection.x - (gameObject.getOriginX() + character.getOriginY()),
                        character.getCenterY());
            }
        }

        movement.setDestination(newMovement.getDestination().x, newMovement.getDestination().y,
                movement.getDestination().x, movement.getDestination().y);

        path.insert(0, newMovement);
    }
}
