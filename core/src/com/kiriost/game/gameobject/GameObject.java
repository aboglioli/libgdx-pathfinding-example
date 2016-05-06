package com.kiriost.game.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.kiriost.game.gameobject.character.Status;
import com.kiriost.game.mechanic.Grid;

/**
 * Created by kiriost on 02/04/16.
 */
public abstract class GameObject extends Actor {
    private GameObjectView view;
    private Status status;

    private Rectangle limit;

    private float duration = 0;

    public GameObject(GameObjectView view) {
        super();
        this.view = view;
        this.status = new Status();

        setWidth(32f);
        setHeight(32f);
        setBounds(0, 0, getWidth(), getHeight());

        setOrigin(Align.center);

        this.limit = new Rectangle(getX() + 8, getY() + 8, getWidth() - 16, getHeight() - 16);
    }

    public float getCenterX() {
        return getX() + getOriginX();
    }

    public float getCenterY() {
        return getY() + getOriginY();
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

    protected void statusChanged(String name, boolean status, boolean changed) {
    }

    protected void movementStarted() {
    }

    protected void movementFinished() {
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(Grid.normalize(x), Grid.normalize(y));
    }

    @Override
    protected void positionChanged() {
        limit.setPosition(getX() + 8, getY() + 8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        update(delta);

        this.duration += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        view.draw(this, batch, parentAlpha);
    }

    protected abstract void update(float delta);
}