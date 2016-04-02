package com.kiriost.game.debug.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by kiriost on 02/04/16.
 */
public class Ghost extends Actor {
    private Texture texture;
    private TextureRegion textureRegion;

    public Ghost() {
        texture = new Texture("badlogic.jpg");
        textureRegion = new TextureRegion(texture);

        setWidth(32);
        setHeight(32);
        setX(8);
        setY(50*8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }
}
