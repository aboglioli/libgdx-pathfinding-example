package com.kiriost.game.graphic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by kiriost on 03/04/16.
 */
public interface IDrawable {
    public TextureRegion getTexture(float duration);
    public TextureRegion getStaticTexture(int index);
}
