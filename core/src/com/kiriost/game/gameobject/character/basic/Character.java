package com.kiriost.game.gameobject.character.basic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.util.Grid;

/**
 * Created by kiriost on 02/04/16.
 */
public abstract class Character extends Actor {
    private CharacterView view;

    private float delta;
    private String status = new String();

    private Array<Movement> movements;

    private float velocity = 240f;

    private boolean moving = false;
    private boolean selected = false;
    private float maxDistance = 0;

    public Character(CharacterView view) {
        super();
        this.view = view;

        movements = new Array<Movement>();

        setWidth(64f);
        setHeight(64f);
        setBounds(0, 0, getWidth(), getHeight());

        setOrigin(Align.center);
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

    public float getDelta() {
        return delta;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
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

    public boolean isMoving() {
        return moving;
    }

    public Movement[] getMovements() {
        return movements.toArray(Movement.class);
    }

    public void move(float x, float y) {
        Movement movement = new Movement();
        if(moving && ModifierKey.L_SHIFT) {
            Movement lastMovement = movements.get(movements.size - 1);

            movement.destination.set(Grid.normalize(x), Grid.normalize(y));
            movement.direction.set(movement.destination.x - lastMovement.destination.x,
                    movement.destination.y - lastMovement.destination.y);
            movement.direction.nor();

            movements.add(movement);
        }
        else {
            if(moving) {
                movements.clear();
            }
            movement.destination.set(Grid.normalize(x), Grid.normalize(y));
            movement.direction.set(movement.destination.x - (getX() + getOriginX()),
                    movement.destination.y - (getY() + getOriginY()));
            movement.direction.nor();

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
            float incX = movement.direction.x * velocity * delta;
            float incY = movement.direction.y * velocity * delta;
            x += incX;
            y += incY;

            setPosition(x, y);

            float distance = movement.destination.dst(getX() + getOriginX(), getY() + getOriginY());

            if (distance < 32) {
                movements.removeValue(movement, true);
                movementFinished();
                if (movements.size == 0) {
                    moving = false;
                }
                else {
                    movementStarted();
                }
            }
        }
    }

    protected abstract void update(float delta);

    protected void statusChanged() {
    }

    protected void selectChanged() {
    }

    protected void movementStarted() {
        setRotation(movements.first().direction.angle() + 90);
    }

    protected void movementFinished() {
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition(delta);
        update(delta);

        this.delta = delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        view.draw(this, batch, parentAlpha);
    }
}
