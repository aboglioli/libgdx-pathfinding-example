package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by kiriost on 11/04/16.
 */
public class CharacterAdapter extends Actor {
    private Vector2 dir;

    public CharacterAdapter() {
        dir = new Vector2(0, -1);
    }

    @Override
    public void setRotation(float degrees) {
        degrees = degrees % 360;
        dir.setAngle(degrees + 270).nor();
        super.setRotation(degrees);
    }

    @Override
    public void rotateBy(float amountInDegrees) {
        dir.rotate(amountInDegrees).nor();
        super.rotateBy(amountInDegrees);
    }

    public Vector2 getDir() {
        return dir.cpy();
    }

    public Vector2 getCenter() {
        Vector2 width = getDir().rotate(90).scl(getWidth());
        Vector2 height = getDir().scl(-getHeight());
        return width.add(height).scl(0.5f);
    }
}
