package com.kiriost.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kiriost.game.AppGame;
import com.kiriost.game.global.Config;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Config.TITLE;
        config.width = Config.WIDTH;
        config.height = Config.HEIGHT;
        new LwjglApplication(new AppGame(), config);
    }
}