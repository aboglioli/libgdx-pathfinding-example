package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.character.basic.CharacterView;

/**
 * Created by kiriost on 08/04/16.
 */
public class PlayerView extends CharacterView {
    public PlayerView(String name) {
        super(name);
        try {
            spriteManager.loadSprite("walk", 256, 256, 0.1f);
            spriteManager.loadSprite("idle", 256, 256, 0.5f);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void update(String characterStatus, boolean selected, boolean moving, float delta) {
        if (!selected && characterStatus.equals("idle"))
            currentFrame = spriteManager.getFrame("idle", 1);
        else if (selected && characterStatus.equals("idle"))
            currentFrame = spriteManager.getFrame("idle", 2);
        else if (!selected && characterStatus.equals("walk"))
            currentFrame = spriteManager.getCurrentFrame("walk", delta);
        else if (selected && characterStatus.equals("walk"))
            currentFrame = spriteManager.getCurrentFrame("walk", delta);
    }
}
