package com.kiriost.game.gameobject.npc;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.manager.SpriteManager;

/**
 * Created by kiriost on 02/04/16.
 */
public class Zombie extends Actor {
    private SpriteManager spriteManager;

    private boolean selected = false;
    private TextureRegion currentTexture;

    public Zombie() {
        spriteManager = new SpriteManager("zombie");

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

        spriteManager.loadAnimation("walk", 256, 256, 0.2f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        currentTexture = spriteManager.getAnimation("walk", delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }
}
