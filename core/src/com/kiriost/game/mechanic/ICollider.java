package com.kiriost.game.mechanic;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by kiriost on 15/04/16.
 */
public interface ICollider {
    Rectangle getLimits();

    boolean collide(ICollider character);
}
