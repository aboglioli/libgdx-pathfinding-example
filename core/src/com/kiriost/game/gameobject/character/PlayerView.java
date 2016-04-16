package com.kiriost.game.gameobject.character;

import com.kiriost.game.graphic.Sprite;
import com.kiriost.game.graphic.SpriteManager;

/**
 * Created by kiriost on 08/04/16.
 */
public class PlayerView extends CharacterView {
    private Sprite idle, walk;

    public PlayerView(String name) {
        super(name);
        SpriteManager spriteManager = SpriteManager.getInstance();

        idle = spriteManager.get("civilian_idle", 256, 256, 0f);
        walk = spriteManager.get("civilian_walk", 256, 256, 0.1f);
    }

    @Override
    public void update(String characterStatus, boolean selected, boolean moving, float delta, float duration) {
        if (moving) {
            addDrawable(walk.getCurrentFrame(duration));
        } else {
            if (selected) {
                addDrawable(idle.getFrame(2));
            } else {
                addDrawable(idle.getFrame(1));
            }
        }
    }
}
