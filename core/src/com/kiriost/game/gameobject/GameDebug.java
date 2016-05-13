package com.kiriost.game.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by kiriost on 13/04/16.
 */
public class GameDebug {
    private ShapeRenderer shapeRenderer;
    private Color color;

    public GameDebug() {
        this.shapeRenderer = new ShapeRenderer();
        float rand = MathUtils.random();
        color = new Color(1, rand, rand, rand);
    }

    public void draw(GameObject character, Batch batch) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
        shapeRenderer.point(character.getX(), character.getY(), 0);
        shapeRenderer.point(character.getX() + character.getWidth(), character.getY(), 0);
        shapeRenderer.point(character.getX(), character.getY() + character.getHeight(), 0);
        shapeRenderer.point(character.getX() + character.getWidth(), character.getY() + character.getHeight(), 0);
//        shapeRenderer.rect(character.getX(), character.getY(), character.getWidth(), character.getHeight());
        shapeRenderer.end();

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(character.getLimit().x, character.getLimit().y, character.getLimit().width,
                character.getLimit().height);
        shapeRenderer.end();

        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(character.getCenterX(), character.getCenterY(),
                character.getLimit().x + character.getLimit().width,
                character.getLimit().y + character.getLimit().height);
        shapeRenderer.end();

        batch.begin();
    }
}
