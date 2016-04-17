package com.kiriost.game.gameobject.character;

/**
 * Created by kiriost on 02/04/16.
 */
public class Npc extends Character {
    public Npc() {
        super(new NpcView());
        setStatus("move", false);
    }

    @Override
    protected void update(float delta) {

    }
}
