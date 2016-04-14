package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.character.basic.Character;

import java.lang.*;

/**
 * Created by kiriost on 02/04/16.
 */
public class Npc extends Character {
    public Npc(String name) {
        super(new NpcView(name));
        setStatus("walk");
    }

    @Override
    protected void update(float delta) {

    }
}
