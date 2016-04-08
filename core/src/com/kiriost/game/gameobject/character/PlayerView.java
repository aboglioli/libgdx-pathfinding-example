package com.kiriost.game.gameobject.character;

/**
 * Created by kiriost on 08/04/16.
 */
public class PlayerView extends CharacterView {
    public PlayerView() {
        super("civilian");
        spriteManager.loadSprite("walk", 256, 256, 0.1f);
        spriteManager.loadSprite("idle", 256, 256, 0.5f);
    }

    @Override
    public void update(String characterStatus, float delta) {
        if (characterStatus.equals("idle"))
            currentFrame = spriteManager.getFrame("idle", 1);
        else if (characterStatus.equals("selected"))
            currentFrame = spriteManager.getFrame("idle", 2);
        else
            currentFrame = spriteManager.getCurrentFrame("walk", delta);
    }
}
