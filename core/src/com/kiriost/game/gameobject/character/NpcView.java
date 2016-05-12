package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.GameView;
import com.kiriost.game.gameobject.Status;
import com.kiriost.game.graphic.Sprite;

/**
 * Created by kiriost on 08/04/16.
 */
public class NpcView extends GameView {
    private Sprite walk;

    public NpcView() {
        super();
    }

    @Override
    public void create() {
        walk = getSprite("zombie_walk", 256, 256, 0.3f);
    }

    @Override
    public void update(Status status, float duration) {
        addDrawable(walk.getCurrentFrame(duration));
    }
}
