package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.GameObjectView;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.CollisionManager;
import com.kiriost.game.mechanic.Movement;

/**
 * Created by kiriost on 06/05/16.
 */
public abstract class Character extends GameObject {
    private float velocity = 240f;

    public Character(GameObjectView view) {
        super(view);

    }

    public void move(float x, float y) {
        Movement movement = new Movement();
        if (getStatus("move") && ModifierKey.L_SHIFT) {

        } else {
            if (getStatus("move")) {

            }

            movement.setDestination(getCenterX(), getCenterY(), x, y);

            setStatus("move", true);

            movementStarted();
        }
    }

    protected void updatePosition(float delta) {

    }

    public void act(float delta) {
        updatePosition(delta);
        super.act(delta);
    }

    protected abstract void update(float delta);
}
