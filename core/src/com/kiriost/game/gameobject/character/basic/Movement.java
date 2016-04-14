package com.kiriost.game.gameobject.character.basic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kiriost on 14/04/16.
 */
public class Movement {
    public Vector2 direction;
    public Vector2 destination;

    public Movement() {
        this.direction = new Vector2();
        this.destination = new Vector2();
    }
}
