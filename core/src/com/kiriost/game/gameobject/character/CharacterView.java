package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.util.ConfigManager;

/**
 * Created by kiriost on 06/04/16.
 */
public abstract class CharacterView {
    private Array<TextureRegion> drawables;

    private boolean debug = false;
    private CharacterViewDebug viewDebug;

    public CharacterView(String characterName) {
        debug = ConfigManager.getInstance().getBooleanProperty("debug");
        viewDebug = new CharacterViewDebug();
        drawables = new Array<TextureRegion>();
    }

    protected void addDrawable(TextureRegion textureRegion) {
        drawables.add(textureRegion);
    }

    public abstract void update(String characterStatus, boolean selected, boolean moving, float delta, float duration);

    public void draw(Character character, Batch batch, float parentAlpha) {
        Color color = character.getColor();
        character.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        update(character.getStatus(), character.isSelected(), character.isMoving(), character.getDelta(),
                character.getDuration());

        batch.setColor(character.getColor());
        for (int i = drawables.size - 1; i >= 0; i--) {
            batch.draw(drawables.pop(),
                    character.getX(), character.getY(),
                    character.getOriginX(), character.getOriginY(),
                    character.getWidth(), character.getHeight(),
                    character.getScaleX(), character.getScaleY(),
                    character.getRotation());
        }

        if (debug || ModifierKey.F12) {
            viewDebug.draw(character, batch);
        }
    }
}
