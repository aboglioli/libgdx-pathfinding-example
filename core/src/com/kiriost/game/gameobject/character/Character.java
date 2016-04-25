package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.CollisionManager;
import com.kiriost.game.mechanic.Movement;

/**
 * Created by kiriost on 02/04/16.
 */
public abstract class Character extends Actor {
    private CharacterView view;
    private Status status;
    private Array<Movement> path;
    private Rectangle limit;
    private Vector2 vectorAlpha; // from origin to top-right corner

    private boolean checkCollision = true;
    private boolean collide = false;

    private float velocity = 240f;

    private float duration = 0;

    public Character(CharacterView view) {
        super();
        this.view = view;
        this.status = new Status();
        this.path = new Array<Movement>();
        this.vectorAlpha = new Vector2();

        setWidth(64f);
        setHeight(64f);
        setBounds(0, 0, getWidth(), getHeight());

        this.limit = new Rectangle(getX() + 8, getY() + 13, getWidth() - 16, getHeight() - 26);

        setOrigin(Align.center);
    }

    public float getCenterX() {
        return getX() + getOriginX();
    }

    public float getCenterY() {
        return getY() + getOriginY();
    }

    public float getAlpha() {
        vectorAlpha.set(limit.x + limit.width, limit.y + limit.height).sub(getCenterX(), getCenterY());
        return vectorAlpha.angle();
    }

    public Rectangle getLimit() {
        return limit;
    }

    public float getDuration() {
        return duration;
    }

    public void resetDuration() {
        duration = 0;
    }

    public boolean getStatus(String name) {
        return status.getStatus(name);
    }

    public void setStatus(String name, boolean status) {
        boolean oldStatus = this.status.getStatus(name);
        this.status.setStatus(name, status);
        statusChanged(name, status, oldStatus != status);
    }

    public Movement[] getPath() {
        return path.toArray(Movement.class);
    }

    public void move(float x, float y) {
        Movement movement = new Movement();
        if (getStatus("move") && ModifierKey.L_SHIFT) {
            Movement lastMovement = path.get(path.size - 1);

            movement.setDestination(lastMovement.getDestination().x, lastMovement.getDestination().y,
                    x, y);

            path.add(movement);
        } else {
            if (getStatus("move")) {
                path.clear();
            }

            movement.setDestination(getCenterX(), getCenterY(), x, y);

            setStatus("move", true);
            path.add(movement);
            movementStarted();
        }
    }

    protected void updatePosition(float delta) {
        Movement movement = path.first();
        float x = getX();
        float y = getY();
        float incX = movement.getDirection().x * velocity * delta;
        float incY = movement.getDirection().y * velocity * delta;

        setPosition(x + incX, y + incY);
        movement.moved(incX, incY);

        collide = CollisionManager.getInstance().collide(this, path);

        if (collide) { // Collision
            setPosition(x, y);
        }

        if (!movement.nextMovement()) { // Movement
            path.removeIndex(0);
            movementFinished();
            if (path.size == 0) {
                setStatus("move", false);
            } else {
                movementStarted();
            }
        }
    }

    protected void statusChanged(String name, boolean status, boolean changed) {
    }

    protected void movementStarted() {
        setRotation(path.first().getDirection().angle() + 90);
    }

    protected void movementFinished() {
    }

    @Override
    protected void positionChanged() {
        limit.setPosition(getX() + 8, getY() + 13);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getStatus("move")) {
            updatePosition(delta);
        }
        update(delta);

        this.duration += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        view.draw(this, batch, parentAlpha);
    }

    protected abstract void update(float delta);
}
