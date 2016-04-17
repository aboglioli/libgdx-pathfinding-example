package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.CollisionManager;
import com.kiriost.game.mechanic.ICollider;
import com.kiriost.game.mechanic.Movement;

/**
 * Created by kiriost on 02/04/16.
 */
public abstract class Character extends Actor implements ICollider {
    private CharacterView view;
    private Status status;
    private Array<Movement> movements;
    private Rectangle limits;

    private float velocity = 240f;

    private float duration = 0;

    public Character(CharacterView view) {
        super();
        this.view = view;
        this.status = new Status();
        this.movements = new Array<Movement>();

        setWidth(64f);
        setHeight(64f);
        setBounds(0, 0, getWidth(), getHeight());

        this.limits = new Rectangle(getX() + 8, getY() + 13, getWidth() - 16, getHeight() - 26);

        setOrigin(Align.center);
    }

    public Rectangle getLimits() {
        return limits;
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

    public void switchStatus(String name) {
        this.status.switchStatus(name);
        statusChanged(name, this.status.getStatus(name), true);
    }

    public Movement[] getMovements() {
        return movements.toArray(Movement.class);
    }

    public void move(float x, float y) {
        Movement movement = new Movement();
        if (getStatus("move") && ModifierKey.L_SHIFT) {
            Movement lastMovement = movements.get(movements.size - 1);

            movement.setDestination(lastMovement.getDestination().x, lastMovement.getDestination().y,
                    x, y);

            movements.add(movement);
        } else {
            if (getStatus("move")) {
                movements.clear();
            }

            movement.setDestination(getX() + getOriginX(), getY() + getOriginY(), x, y);

            setStatus("move", true);
            movements.add(movement);
            movementStarted();
        }
    }

    protected void updatePosition(float delta) {
        Movement movement = movements.first();
        float x = getX();
        float y = getY();
        float incX = movement.getDirection().x * velocity * delta;
        float incY = movement.getDirection().y * velocity * delta;

        setPosition(x + incX, y + incY);
        movement.moved(incX, incY);

        boolean collide = CollisionManager.getInstance().collide(this);

        if (collide) { // Collision
            setPosition(x, y);

            float dirAngle = movement.getDirection().angle();
            System.out.println(dirAngle);
            if (dirAngle < 90) {

            }

            movements.clear();
            movementFinished();
            setStatus("move", false);
        }
        else if (!movement.nextMovement()) { // Movement
            movements.removeIndex(0);
            movementFinished();
            if (movements.size == 0) {
                setStatus("move", false);
            } else {
                movementStarted();
            }
        }
    }

    protected void statusChanged(String name, boolean status, boolean changed) {
    }

    protected void movementStarted() {
        setRotation(movements.first().getDirection().angle() + 90);
    }

    protected void movementFinished() {
    }

    @Override
    protected void positionChanged() {
        limits.setPosition(getX() + 8, getY() + 13);
    }

    @Override
    public boolean collide(ICollider character) {
        return limits.overlaps(character.getLimits());
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
