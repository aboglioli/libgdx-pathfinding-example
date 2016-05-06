package com.kiriost.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kiriost.game.AppGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Survive it!";
        config.width = 800;
        config.height = 600;

        new LwjglApplication(new AppGame(), config);
    }
}