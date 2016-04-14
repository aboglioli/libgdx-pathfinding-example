package com.kiriost.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kiriost.game.AppGame;
import com.kiriost.game.manager.ConfigManager;

public class DesktopLauncher {
    public static void main(String[] arg) {
        ConfigManager configManager = ConfigManager.getInstance();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = configManager.getProperty("title");
        config.width = configManager.getIntProperty("width");
        config.height = configManager.getIntProperty("height");

        new LwjglApplication(new AppGame(), config);
    }
}