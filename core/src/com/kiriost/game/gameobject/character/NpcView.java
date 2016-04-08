package com.kiriost.game.gameobject.character;

/**
 * Created by kiriost on 08/04/16.
 */
public class NpcView extends CharacterView {
    public NpcView() {
        super("zombie");
        spriteManager.loadSprite("walk", 256, 256, 0.2f);
    }

    @Override
    public void update(String characterStatus, float delta) {
        currentFrame = spriteManager.getCurrentFrame(characterStatus, delta);
    }
}
