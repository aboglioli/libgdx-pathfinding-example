package com.kiriost.game.gameobject.character.basic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiriost.game.manager.ConfigManager;
import com.kiriost.game.manager.SpriteManager;

import java.lang.*;

/**
 * Created by kiriost on 06/04/16.
 */
public abstract class CharacterView {
    protected SpriteManager spriteManager;
    protected TextureRegion currentFrame;

    private boolean debug = false;
    private CharacterViewDebug viewDebug;

    public CharacterView(String characterName) {
        spriteManager = new SpriteManager(characterName);
        debug = ConfigManager.getInstance().getBooleanProperty("debug");

        viewDebug = new CharacterViewDebug();
    }

    public abstract void update(String characterStatus, boolean selected, float delta);

    public void draw(Character character, Batch batch, float parentAlpha) {
        Color color = character.getColor();
        character.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        update(character.getStatus(), character.isSelected(), character.getDelta());

        batch.setColor(character.getColor());
        batch.draw(currentFrame, character.getX(), character.getY(),
                character.getOriginX(), character.getOriginY(),
                character.getWidth(), character.getHeight(), character.getScaleX(), character.getScaleY(),
                character.getRotation());

        if(debug) {
            viewDebug.draw(character, batch);
        }
    }
}
