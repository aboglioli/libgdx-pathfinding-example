package com.kiriost.game.actor;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by kiriost on 02/04/16.
 */
public class SelectedActor extends AnimatedActor {
    public SelectedActor() {
        super("selected_circle.png", 320, 320);
        animation.setFrameDuration(.4f);

        Color color = getColor();
        setColor(color.r, color.g, color.b, .2f);
    }
}
