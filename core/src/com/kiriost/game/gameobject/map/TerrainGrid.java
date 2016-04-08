package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by kiriost on 02/04/16.
 */
public class TerrainGrid {
    private ShapeRenderer shapeRenderer;

    public TerrainGrid() {
        shapeRenderer = new ShapeRenderer();
    }

    public void draw(Batch batch, float terrainX, float terrainY, float terrainWidth, float terrainHeight, float square) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.setColor(0.4f, 0.2f, 0.2f, 0.5f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        float width = terrainWidth;
        float height = terrainHeight;
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
