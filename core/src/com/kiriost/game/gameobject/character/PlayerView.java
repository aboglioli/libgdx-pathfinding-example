package com.kiriost.game.gameobject.character;

import com.kiriost.game.gameobject.GameView;
import com.kiriost.game.gameobject.Status;
import com.kiriost.game.graphic.Sprite;

/**
 * Created by kiriost on 08/04/16.
 */
public class PlayerView extends GameView {
    private Sprite idle, walk;

    public PlayerView() {
        super();
    }

    @Override
    public void create() {
        idle = getSprite("civilian_idle", 256, 256, 0f);
        walk = getSprite("civilian_walk", 256, 256, 0.1f);
//        selected_circle = spriteManager.get("selected_circle",
    }

    @Override
    public void update(Status status, float duration) {
        if (status.get("move")) {
            addDrawable(walk.getCurrentFrame(duration));
        } else {
            if (status.get("select")) {
                addDrawable(idle.getFrame(2));
//                addDrawable(selected_circle.getCurrentFrame(character.duration()));
            } else {
                addDrawable(idle.getFrame(1));
            }
        }
    }
}