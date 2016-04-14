package com.kiriost.game.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiriost.game.manager.ConfigManager;

/**
 * Created by kiriost on 03/04/16.
 */
public class Sprite {
    protected com.badlogic.gdx.graphics.g2d.Animation animation;
    private TextureRegion[] textureRegions;

    private static final String spriteFolder = ConfigManager.getInstance().getProperty("spriteFolder");
    private static final String spriteExtension = ConfigManager.getInstance().getProperty("spriteExtension");

    public Sprite(String texture, int tileWidth, int tileHeight, float frameDuration) {
        TextureRegion[][] temp = TextureRegion.split(new Texture(spriteFolder + "/" + texture + "." + spriteExtension),
                tileWidth, tileHeight);

        textureRegions = new TextureRegion[temp.length * temp[0].length];
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                textureRegions[index++] = temp[i][j];
            }
        }

        animation = new Animation(frameDuration, textureRegions);
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