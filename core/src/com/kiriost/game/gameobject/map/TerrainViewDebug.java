package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kiriost.game.manager.ConfigManager;

/**
 * Created by kiriost on 02/04/16.
 */
public class TerrainViewDebug {
    private ShapeRenderer shapeRenderer;
    private int square;

    public TerrainViewDebug() {
        shapeRenderer = new ShapeRenderer();
        square = ConfigManager.getInstance().getIntProperty("square");
    }

    public void draw(Terrain terrain, Batch batch) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.setColor(0.4f, 0.2f, 0.2f, 0.5f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        float width = terrain.getWidth();
        float height = terrain.getHeight();
        for (int i = 0; i < width; i += square) {
            shapeRenderer.line(i, terrain.getY(), i, height);
        }
        for (int i = 0; i < height; i += square) {
            shapeRenderer.line(terrain.getX(), i, width, i);
        }
        shapeRenderer.end();

        batch.begin();
    }
}
