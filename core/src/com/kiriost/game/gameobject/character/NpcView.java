package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.character.basic.CharacterView;

/**
 * Created by kiriost on 08/04/16.
 */
public class NpcView extends CharacterView {
    public NpcView(String name) {
        super(name);
        try {
            spriteManager.loadSprite("walk", 256, 256, 0.2f);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void update(String characterStatus, boolean selected, boolean moving, float delta) {
        currentFrame = spriteManager.getCurrentFrame(characterStatus, delta);
    }
}
