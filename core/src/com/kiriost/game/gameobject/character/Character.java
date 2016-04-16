package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.CollisionManager;
import com.kiriost.game.mechanic.Movement;

/**
 * Created by kiriost on 02/04/16.
 */
public abstract class Character extends Actor implements ICollider {
    private CharacterView view;

    private float delta = 0;
    private float duration = 0;

    private String status = new String();
    private Rectangle limits;

    private Array<Movement> movements;

    private float velocity = 240f;
    private boolean moving = false;
    private boolean selected = false;

    public Character(CharacterView view) {
        super();
        this.view = view;

        movements = new Array<Movement>();

        setWidth(64f);
        setHeight(64f);
        setBounds(0, 0, getWidth(), getHeight());

        limits = new Rectangle(getX() + 8, getY() + 13, getWidth() - 16, getHeight() - 26);

        setOrigin(Align.center);
    }

    @Override
    protected void positionChanged() {
        limits.setPosition(getX() + 8, getY() + 13);
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
        selectChanged();
    }

    public void deselect() {
        selected = false;
        selectChanged();
    }

    public Rectangle getLimits() {
        return limits;
    }

    public float getDelta() {
        return delta;
    }

    public float getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!this.status.equals(status)) {
            this.status = status;
            statusChanged();
        }
    }

    public Movement[] getMovements() {
        return movements.toArray(Movement.class);
    }

    public void move(float x, float y) {
        Movement movement = new Movement();
        if (moving && ModifierKey.L_SHIFT) {
            Movement lastMovement = movements.get(movements.size - 1);

            movement.setDestination(lastMovement.getDestination().x, lastMovement.getDestination().y,
                    x, y);

            movements.add(movement);
        } else {
            if (moving) {
                movements.clear();
            }

            movement.setDestination(getX() + getOriginX(), getY() + getOriginY(), x, y);

            moving = true;
            movements.add(movement);
            movementStarted();
        }
    }

    protected void updatePosition(float delta) {
        if (moving) {
            Movement movement = movements.first();
            float x = getX();
            float y = getY();
            float incX = movement.getDirection().x * velocity * delta;
            float incY = movement.getDirection().y * velocity * delta;

            setPosition(x + incX, y + incY);

            boolean collide = CollisionManager.getInstance().collide(this);

            if (collide)
                setPosition(x, y);

            movement.moved(incX, incY);

            if (collide) {
                movements.clear();
                movementFinished();
                moving = false;
            } else if (!movement.nextMovement()) {
                movements.removeValue(movement, true);
                movementFinished();
                if (movements.size == 0) {
                    moving = false;
                } else {
                    movementStarted();
                }
            }
        }
    }

    protected void statusChanged() {
        duration = 0;
    }

    protected void selectChanged() {

    }

    protected void movementStarted() {
        setRotation(movements.first().getDirection().angle() + 90);
    }

    protected void movementFinished() {
    }

    @Override
    public boolean collide(ICollider character) {
        return limits.overlaps(character.getLimits());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition(delta);
        update(delta);

        this.delta = delta;
        this.duration += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        view.draw(this, batch, parentAlpha);
    }

    protected abstract void update(float delta);
}
