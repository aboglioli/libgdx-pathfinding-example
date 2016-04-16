package com.kiriost.game.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by kiriost on 03/04/16.
 */
public class Sprite {
    private Animation animation;
    private TextureRegion[] textureRegions;
    private boolean animated;

    public Sprite(String texture, int tileWidth, int tileHeight, float frameDuration) {
        this(texture, tileHeight, tileHeight);
        animation = new Animation(frameDuration, textureRegions);
        animated = true;
    }

    public Sprite(String texture, int tileWidth, int tileHeight) {
        TextureRegion[][] temp = TextureRegion.split(new Texture(texture), tileWidth, tileHeight);

        textureRegions = new TextureRegion[temp.length * temp[0].length];
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                textureRegions[index++] = temp[i][j];
            }
        }

        animated = false;
    }

    public boolean isAnimated() {
        return animated;
    }

    public TextureRegion getCurrentFrame(float duration) {
        return animation.getKeyFrame(duration, true);
    }

    public TextureRegion getFrame(int index) {
        if (index >= textureRegions.length)
            return textureRegions[textureRegions.length - 1];
        return textureRegions[index];
    }
}