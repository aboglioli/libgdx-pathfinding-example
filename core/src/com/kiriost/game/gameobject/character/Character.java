package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by kiriost on 02/04/16.
 */
public abstract class Character extends Actor {
    private CharacterView view;

    private float delta;
    private String status = new String();

    private Vector2 moveDirection;
    private Rectangle clicked;
    private boolean moving = false;
    private float velocity = 240f;

    public Character(CharacterView view) {
        super();
        this.view = view;

        moveDirection = new Vector2();
        clicked = new Rectangle();

        setWidth(50f);
        setHeight(50f);
        setBounds(0, 0, getWidth(), getHeight());

        setOrigin(Align.center);
    }

    public void move(float x, float y) {
        moveDirection.set(x - (getX() + getOriginX()), y - (getY() + getOriginY()));
        setRotation(moveDirection.angle() + 90);

        clicked.set(getX() + getOriginX() + moveDirection.x - 5, getY() + getOriginY() + moveDirection.y - 5, 10, 10);

        moveDirection.nor();
        moving = true;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
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

    protected void updatePosition(float delta) {
        if (moving) {
            float x = getX();
            float y = getY();
            float moveX = moveDirection.x * velocity * delta;
            float moveY = moveDirection.y * velocity * delta;
            setPosition(x + moveX, y + moveY);
            if (clicked.contains(getX() + getOriginX(), getY() + getOriginY())) {
                moving = false;
            }
        }
    }

    protected abstract void update(float delta);

    protected void statusChanged() {
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
