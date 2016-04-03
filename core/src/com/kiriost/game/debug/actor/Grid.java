package com.kiriost.game.debug.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by kiriost on 02/04/16.
 */
public class Grid {
    private ShapeRenderer shapeRenderer;
    private int terrainRows, terrainCols, square;

    public Grid(int terrainRows, int terrainCols, int square) {
        shapeRenderer = new ShapeRenderer();

        this.terrainRows = terrainRows;
        this.terrainCols = terrainCols;
        this.square = square;
    }

    public void draw(Batch batch, float terrainX, float terrainY) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.setColor(0.4f, 0.2f, 0.2f, 0.5f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        int width = terrainRows * square;
        int height = terrainCols * square;
        for (int i = 0; i < width; i += square) {
            shapeRenderer.line(i, terrainY, i, height);
        }
        for (int i = 0; i < height; i += square) {
            shapeRenderer.line(terrainX, i, width, i);
        }
        shapeRenderer.end();

        batch.begin();
    }
}
