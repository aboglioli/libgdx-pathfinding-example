package com.kiriost.game.manager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.kiriost.game.graphic.Sprite;

/**
 * Created by kiriost on 04/04/16.
 */
public class SpriteManager {
    private static ObjectMap<String, Sprite> animations;

    static {
        animations = new ObjectMap<String, Sprite>();
    }

    private String prefix;
    private String lastAnimation = null;
    private float duration = 0;

    public SpriteManager(String characterName) {
        this.prefix = characterName + "_";
    }

    public void loadSprite(String animationName, int width, int height, float frameDuration) {
        if (!animations.containsKey(prefix + animationName)) {
            Sprite animation = new Sprite(prefix + animationName + ".png", width, height, frameDuration);
            if (animation == null)
                throw new NullPointerException("Sprite file was not found");

            animations.put(prefix + animationName, animation);
        }
    }

    public TextureRegion getCurrentFrame(String animationName, float delta) {
        if (lastAnimation != animationName)
            duration = 0;

        if (!animations.containsKey(prefix + animationName))
            throw new NullPointerException("Sprite '" + prefix + animationName + "'was not found");

        lastAnimation = animationName;
        duration += delta;

        Sprite animation = animations.get(prefix + animationName);
        return animation.getCurrentFrame(duration);
    }

    public TextureRegion getFrame(String animationName, int number) {
        if (lastAnimation != animationName)
            duration = 0;

        if (!animations.containsKey(prefix + animationName))
            throw new NullPointerException("Sprite texture '" + prefix + animationName + "' was not found");

        lastAnimation = animationName;

        Sprite animation = animations.get(prefix + animationName);
        return animation.getFrame(number);
    }

    public TextureRegion getFrame(String textureName) {
        if (!animations.containsKey(prefix + textureName))
            throw new NullPointerException("Sprite texture '" + prefix + textureName + "' was not found");

        Sprite animation = animations.get(prefix + textureName);
        return animation.getFrame(0);
    }
}