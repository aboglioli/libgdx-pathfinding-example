package com.kiriost.game.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;

/**
 * Created by kiriost on 06/04/16.
 */
public abstract class GameObjectView {
    private Array<TextureRegion> drawables;

    private GameObjectViewDebug viewDebug;

    public GameObjectView() {
        viewDebug = new GameObjectViewDebug();
        drawables = new Array<TextureRegion>();
    }

    protected void addDrawable(TextureRegion textureRegion) {
        drawables.add(textureRegion);
    }

    public abstract void update(GameObject character);

    public void draw(GameObject character, Batch batch, float parentAlpha) {
        Color color = character.getColor();
        character.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        update(character);

        batch.setColor(character.getColor());
        for (int i = drawables.size - 1; i >= 0; i--) {
            batch.draw(drawables.pop(),
                    character.getX(), character.getY(),
                    character.getOriginX(), character.getOriginY(),
                    character.getWidth(), character.getHeight(),
                    character.getScaleX(), character.getScaleY(),
                    character.getRotation());
        }

        if (ModifierKey.F12) {
            viewDebug.draw(character, batch);
        }
    }
}
