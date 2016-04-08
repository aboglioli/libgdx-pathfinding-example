package com.kiriost.game.gameobject.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kiriost.game.manager.SpriteManager;

/**
 * Created by kiriost on 03/04/16.
 */
public class Barracks extends Actor {
    private final SpriteManager spriteManager;
    private TextureRegion textureRegion;

    public Barracks() {
        spriteManager = new SpriteManager("barracks");
        spriteManager.loadSprite("building", 512, 512, 0f);
        textureRegion = spriteManager.getFrame("building", 0);

        setPosition(150f, 150f);
        setWidth(256f);
        setHeight(256f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }
}
