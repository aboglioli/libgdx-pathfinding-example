package com.kiriost.game.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by kiriost on 02/04/16.
 */
public class Civilian extends AnimatedActor {
    private AnimatedActor selectedActor;
    private boolean selected = false;

    public Civilian() {
        super("civilian.png", 320, 320);
        setBounds(0, 0, getWidth(), getHeight());

        selectedActor = new SelectedActor();

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selected = !selected;
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        selectedActor.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(selected) {
            selectedActor.draw(batch, parentAlpha);
        }

        super.draw(batch, parentAlpha);
    }
}
