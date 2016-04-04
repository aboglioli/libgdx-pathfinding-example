package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.manager.SpriteManager;

/**
 * Created by kiriost on 02/04/16.
 */
public class Civilian extends Actor {
    private SpriteManager spriteManager;

    private boolean selected = true;
    private TextureRegion currentTexture;

    public Civilian() {
        spriteManager = new SpriteManager("civilian");

        setBounds(0, 0, getWidth(), getHeight());

        setWidth(40f);
        setHeight(40f);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selected = !selected;
                return true;
            }
        });

        spriteManager.loadAnimation("walk", 256, 256, 0.1f);
        spriteManager.loadAnimation("idle", 256, 256, 0.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (selected) {
            currentTexture = spriteManager.getAnimation("walk", delta);
        } else {
            currentTexture = spriteManager.getTexture("idle", 2);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }
}
