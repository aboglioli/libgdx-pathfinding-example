package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.GameObject;

/**
 * Created by kiriost on 02/04/16.
 */
public class Npc extends GameObject {
    public Npc() {
        super(new NpcView());
        setStatus("move", false);
    }

    @Override
    protected void update(float delta) {

    }
}
