package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.GameView;

/**
 * Created by kiriost on 02/04/16.
 */
public class Npc extends GameObject {
    public Npc(GameView view) {
        super(view);

        setStatus("move", false);
    }

    @Override
    protected void update(float delta) {

    }
}
