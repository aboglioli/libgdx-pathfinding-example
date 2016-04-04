package com.kiriost.game.manager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.kiriost.game.graphic.Animation;
import com.kiriost.game.graphic.IDrawable;
import com.kiriost.game.graphic.StaticTexture;

/**
 * Created by kiriost on 04/04/16.
 */
public class SpriteManager {
    private static ObjectMap<String, IDrawable> animations;
    private String prefix;

    private String lastAnimation = null;
    private float duration = 0;

    static {
        animations = new ObjectMap<String, IDrawable>();
    }

    public SpriteManager(String actorName) {
        this.prefix = actorName + "_";
    }

    public void loadAnimation(String animationName, int width, int height, float frameDuration) {
        if (!animations.containsKey(prefix + animationName)) {
            IDrawable animation = new Animation(prefix + animationName + ".png", width, height, frameDuration);
            if (animation == null)
                throw new NullPointerException("Animation file was not found");

            animations.put(prefix + animationName, animation);
        }
    }

    public void loadTexture(String textureName) {
        if (!animations.containsKey(prefix + textureName)) {
            IDrawable texture = new StaticTexture(prefix + textureName + ".png");
            if (texture == null)
                throw new NullPointerException("Texture file was not found");

            animations.put(prefix + textureName, texture);
        }
    }

    public TextureRegion getAnimation(String animationName, float delta) {
        if(lastAnimation != animationName)
            duration = 0;

        if (!animations.containsKey(prefix + animationName))
            throw new NullPointerException("Animation '" + prefix + animationName + "'was not found");

        lastAnimation = animationName;
        duration += delta;

        IDrawable animation = animations.get(prefix + animationName);
        return animation.getTexture(duration);
    }

    public TextureRegion getTexture(String animationName, int number) {
        if(lastAnimation != animationName)
            duration = 0;

        if (!animations.containsKey(prefix + animationName))
            throw new NullPointerException("Animation texture '" + prefix + animationName + "' was not found");

        lastAnimation = animationName;

        IDrawable animation = animations.get(prefix + animationName);
        return animation.getStaticTexture(number);
    }

    public TextureRegion getTexture(String textureName) {
        if (!animations.containsKey(prefix + textureName))
            throw new NullPointerException("Texture was not found");

        IDrawable animation = animations.get(prefix + textureName);
        return animation.getStaticTexture(0);
    }
}