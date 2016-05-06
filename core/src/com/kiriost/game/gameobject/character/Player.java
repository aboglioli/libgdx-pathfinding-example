package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.WorldManager;

/**
 * Created by kiriost on 08/04/16.
 */
public class Player extends Character {

    public Player() {
        super(new PlayerView());
        setStatus("move", false);

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setStatus("select", true);
                return true;
            }
        });
    }

    @Override
    protected void update(float delta) {
    }

    @Override
    protected void statusChanged(String name, boolean status, boolean changed) {
        if (name.equals("select") && status) {
            WorldManager.getInstance().setSelectedPlayer(this);
        } else if (name.equals("move") && changed) {
            resetDuration();
        }
    }

    @Override
    protected void movementStarted() {
        super.movementStarted();
        setStatus("move", true);
    }

    @Override
    protected void movementFinished() {
        setStatus("move", false);
    }
}
