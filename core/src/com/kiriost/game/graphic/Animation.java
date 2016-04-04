package com.kiriost.game.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by kiriost on 03/04/16.
 */
public class Animation implements IDrawable {
    // Graphics and animation
    private TextureRegion[] textureRegions;

    protected com.badlogic.gdx.graphics.g2d.Animation animation;

    public Animation(String texture, int tileWidth, int tileHeight, float frameDuration) {
        TextureRegion[][] temp = TextureRegion.split(new Texture("sprite/" + texture), tileWidth, tileHeight);

        textureRegions = new TextureRegion[temp.length * temp[0].length];
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                textureRegions[index++] = temp[i][j];
            }
        }

        animation = new com.badlogic.gdx.graphics.g2d.Animation(frameDuration, textureRegions);
    }

    @Override
    public TextureRegion getTexture(float duration) {
        return animation.getKeyFrame(duration, true);
    }

    @Override
    public TextureRegion getStaticTexture(int index) {
        return textureRegions[index];
    }
}