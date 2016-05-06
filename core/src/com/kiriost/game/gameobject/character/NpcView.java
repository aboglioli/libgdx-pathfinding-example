package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.GameObjectView;
import com.kiriost.game.graphic.Sprite;
import com.kiriost.game.graphic.SpriteManager;

/**
 * Created by kiriost on 08/04/16.
 */
public class NpcView extends GameObjectView {
    private Sprite walk;

    public NpcView() {
        super();
        SpriteManager spriteManager = SpriteManager.getInstance();

        walk = spriteManager.get("zombie_walk", 256, 256, 0.3f);

    }

    @Override
    public void update(GameObject character) {
        addDrawable(walk.getCurrentFrame(character.getDuration()));
    }
}
