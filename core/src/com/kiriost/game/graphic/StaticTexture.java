package com.kiriost.game.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by kiriost on 03/04/16.
 */
public class StaticTexture implements IDrawable {
    private TextureRegion textureRegion;

    public StaticTexture(String texturePath) {
        textureRegion = new TextureRegion(new Texture("sprite/" + texturePath));
    }

    @Override
    public TextureRegion getTexture(float duration) {
        return textureRegion;
    }

    @Override
    public TextureRegion getStaticTexture(int index) {
        return textureRegion;
    }
}
