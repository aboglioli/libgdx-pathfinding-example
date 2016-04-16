package com.kiriost.game.gameobject.character;

import com.kiriost.game.graphic.Sprite;
import com.kiriost.game.graphic.SpriteManager;

/**
 * Created by kiriost on 08/04/16.
 */
public class NpcView extends CharacterView {
    private Sprite walk;

    public NpcView(String name) {
        super(name);
        SpriteManager spriteManager = SpriteManager.getInstance();

        walk = spriteManager.get("zombie_walk", 256, 256, 0.3f);

    }

    @Override
    public void update(String characterStatus, boolean selected, boolean moving, float delta, float duration) {
        addDrawable(walk.getCurrentFrame(duration));
    }
}
