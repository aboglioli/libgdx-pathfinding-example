package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.gameobject.character.basic.Character;
import com.kiriost.game.manager.WorldManager;

/**
 * Created by kiriost on 08/04/16.
 */
public class Player extends Character {

    public Player(String name) {
        super(new PlayerView(name));
        setStatus("idle");

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                select();
                return true;
            }
        });
    }

    @Override
    protected void selectChanged() {
        if (isSelected())
            WorldManager.getInstance().setSelectedPlayer(this);
    }

    @Override
    protected void update(float delta) {
    }

    @Override
    protected void statusChanged() {
    }

    @Override
    protected void movementStarted() {
        super.movementStarted();
        setStatus("walk");
    }

    @Override
    protected void movementFinished() {
        super.movementFinished();
        setStatus("idle");
    }
}
