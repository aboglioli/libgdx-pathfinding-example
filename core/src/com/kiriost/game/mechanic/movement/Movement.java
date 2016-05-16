package com.kiriost.game.mechanic.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiriost on 14/04/16.
 */
public class Movement implements Pool.Poolable {
    private Vector2 direction;

    private Vector2 destination;
    private float distance;

    public Movement() {
        this.direction = new Vector2();
        this.destination = new Vector2();
        this.distance = 0;
    }

    public Vector2 getDestination() {
        return this.destination;
    }

    public void setDestination(float origX, float origY, float dstX, float dstY) {
        this.destination.set(dstX, dstY);
        distance = this.direction.set(dstX - origX, dstY - origY).len();
        this.direction.nor();
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void moved(float x, float y) {
        float moved = Vector2.len(x, y);
        distance -= moved;
    }

    public boolean nextMovement() {
        return distance > 0;
    }

    @Override
    public void reset() {
        direction.set(0, 0);
        destination.set(0, 0);
    }

    @Override
    public String toString() {
        String msg = direction.x + " -- " + direction.y;
        return msg;
    }
}
