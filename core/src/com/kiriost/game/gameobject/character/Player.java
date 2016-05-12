package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.GameView;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.Step;

/**
 * Created by kiriost on 08/04/16.
 */
public class Player extends GameObject {

    public Player(GameView view) {
        super(view);
        System.out.println("NEW");
        setStatus("move", false);

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setStatus("select", true);
                return true;
            }
        });
    }

    protected void updatePosition(float delta) {
    }

    public void move(float x, float y) {
        Step step = new Step();
        if (getStatus("move") && ModifierKey.L_SHIFT) {

        } else {
            if (getStatus("move")) {

            }

            step.setDestination(getCenterX(), getCenterY(), x, y);

            setStatus("move", true);

            movementStarted();
        }
    }

    @Override
    protected void update(float delta) {
        updatePosition(delta);
    }

    @Override
    protected void statusChanged(String name, boolean status, boolean changed) {
        if (name.equals("select") && status) {
//            World.getInstance().setSelectedPlayer(this);
        } else if (name.equals("move") && changed) {
            resetDuration();
        }
    }

    @Override
    protected void movementStarted() {
        setStatus("move", true);
    }

    @Override
    protected void movementFinished() {
        setStatus("move", false);
    }


}