package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kiriost.game.debug.actor.Grid;

/**
 * Created by kiriost on 02/04/16.
 */
public class Terrain extends Actor {
    private final int terrainRows = 128;
    private final int terrainCols = 128;
    private final int square = 16;
    private Texture texture;
    private TextureRegion textureRegion;
    private Grid grid;

    private final boolean DEBUG = false;

    public Terrain() {
        texture = new Texture("map/terrain_tiles.png");
        textureRegion = new TextureRegion(texture, 0, 0, 143, 143);

        grid = new Grid(terrainRows, terrainCols, square);

        setX(0f);
        setY(0f);
        setWidth(square);
        setHeight(square);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        setColor(color.r, color.g, color.b, color.a * parentAlpha);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int width = terrainRows * square;
        int height = terrainCols * square;
        for (int i = 0; i < width; i += square) {
            for (int j = 0; j < height; j += square) {
                batch.draw(textureRegion, getX() + i, getY() + j, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                        getScaleY(), getRotation());
            }
        }

        if (DEBUG)
            grid.draw(batch, getX(), getY());

    }
}
