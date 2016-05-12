package com.kiriost.game.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.graphic.Sprite;
import com.kiriost.game.graphic.SpriteManager;
import com.kiriost.game.input.ModifierKey;

/**
 * Created by kiriost on 06/04/16.
 */
public abstract class GameView {
    private SpriteManager spriteManager;
    private Array<TextureRegion> drawables;

    private GameDebug viewDebug;


    public GameView() {
        spriteManager = SpriteManager.getInstance();
        drawables = new Array<TextureRegion>();

        viewDebug = new GameDebug();

        create();
    }

    protected Sprite getSprite(String name, int width, int height, float duration) {
        return spriteManager.get(name, width, height, duration);
    }

    protected void addDrawable(TextureRegion textureRegion) {
        drawables.add(textureRegion);
    }

    public abstract void create();

    public abstract void update(Status status, float duration);

    public void draw(GameObject character, Batch batch, float parentAlpha, Status status, float duration) {
        Color color = character.getColor();
        character.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        update(status, duration);

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
