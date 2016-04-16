package com.kiriost.game.graphic;

import com.badlogic.gdx.utils.ObjectMap;
import com.kiriost.game.util.ConfigManager;

/**
 * Created by kiriost on 16/04/16.
 */
public class SpriteManager {
    private static SpriteManager instance;
    private final String spriteFolder = ConfigManager.getInstance().getProperty("spriteFolder");
    private final String imageExtension = ConfigManager.getInstance().getProperty("imageExtension");
    private ObjectMap<String, Sprite> sprites;

    private SpriteManager() {
        sprites = new ObjectMap<String, Sprite>();
    }

    public static SpriteManager getInstance() {
        if (instance == null) {
            instance = new SpriteManager();
        }
        return instance;
    }

    /**
     * Sprites and animations
     */
    public Sprite get(String name, int width, int height, float frameDuration) {
        Sprite sprite;
        if (sprites.containsKey(name)) {
            sprite = sprites.get(name);
        } else {
            sprite = new Sprite(spriteFolder + "/" + name + "." + imageExtension, width, height, frameDuration);
            sprites.put(name, sprite);
        }

        return sprite;
    }
}
