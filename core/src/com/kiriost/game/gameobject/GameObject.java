package com.kiriost.game.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.kiriost.game.mechanic.Grid;

/**
 * Created by kiriost on 02/04/16.
 */
public abstract class GameObject extends Actor {
    private GameView view;
    private Status status;

    private Rectangle limit;
    private float duration = 0;

    public GameObject(GameView view) {
        super();
        this.view = view;
        this.status = new Status();

        setWidth(Grid.square * 2);
        setHeight(Grid.square * 2);
        setBounds(0, 0, getWidth(), getHeight());

        setOrigin(Align.center);

        this.limit = new Rectangle(getX() + Grid.square / 2, getY() + Grid.square / 2,
                getWidth() - Grid.square, getHeight() - Grid.square);
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

    public boolean getStatus(String name) {
        return status.get(name);
    }

    public void setStatus(String name, boolean status) {
        boolean oldStatus = this.status.get(name);
        this.status.set(name, status);
        statusChanged(name, status, oldStatus != status);
    }

    protected void resetDuration() {
        duration = 0;
    }

    protected void statusChanged(String name, boolean status, boolean changed) {
    }

    protected void movementStarted() {
    }

    protected void movementFinished() {
    }

    @Override
    public void setX(float x) {
        super.setX(Grid.normalize(x) - limit.width / 2);
    }

    @Override
    public void setY(float y) {
        super.setY(Grid.normalize(y) - limit.height / 2);
    }

    @Override
    public void setPosition(float x, float y) {
//        super.setPosition(x, y);
        super.setPosition(Grid.normalize(x) - limit.width / 2, Grid.normalize(y) - limit.height / 2);
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
        view.draw(this, batch, parentAlpha, status, duration);
    }

    protected abstract void update(float delta);
}
