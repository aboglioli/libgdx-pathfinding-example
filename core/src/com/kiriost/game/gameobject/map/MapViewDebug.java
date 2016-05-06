package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kiriost.game.mechanic.Grid;

/**
 * Created by kiriost on 02/04/16.
 */
public class MapViewDebug {
    private ShapeRenderer shapeRenderer;
    private int square = Grid.square;

    public MapViewDebug() {
        shapeRenderer = new ShapeRenderer();
    }

    public void draw(Map map, Batch batch) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.setColor(0.4f, 0.2f, 0.2f, 0.5f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        float width = map.getWidth();
        float height = map.getHeight();
        for (int i = 0; i < width; i += square) {
            shapeRenderer.line(i, map.getY(), i, height);
        }
        for (int i = 0; i < height; i += square) {
            shapeRenderer.line(map.getX(), i, width, i);
        }
        shapeRenderer.end();

        batch.begin();
    }
}
