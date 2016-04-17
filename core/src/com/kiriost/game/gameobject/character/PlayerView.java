package com.kiriost.game.gameobject.character;

import com.kiriost.game.graphic.Sprite;
import com.kiriost.game.graphic.SpriteManager;

/**
 * Created by kiriost on 08/04/16.
 */
public class PlayerView extends CharacterView {
    private Sprite idle, walk;

    public PlayerView() {
        super();
        SpriteManager spriteManager = SpriteManager.getInstance();

        idle = spriteManager.get("civilian_idle", 256, 256, 0f);
        walk = spriteManager.get("civilian_walk", 256, 256, 0.1f);
//        selected_circle = spriteManager.get("selected_circle", 256, 256, 0.1f);
    }

    @Override
    public void update(Character character) {
        if (character.getStatus("move")) {
            addDrawable(walk.getCurrentFrame(character.getDuration()));
        } else {
            if (character.getStatus("select")) {
                addDrawable(idle.getFrame(2));
//                addDrawable(selected_circle.getCurrentFrame(character.getDuration()));
            } else {
                addDrawable(idle.getFrame(1));
            }
        }
    }
}