package com.kiriost.game.actor;

/**
 * Created by kiriost on 02/04/16.
 */
public class Zombie extends AnimatedActor {
    public Zombie() {
        super("zombie.png", 320, 320);
        animation.setFrameDuration(0.25f);
    }
}
