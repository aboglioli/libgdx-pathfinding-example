package com.kiriost.game.gameobject.character.basic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kiriost on 13/04/16.
 */
public class CharacterViewDebug {
    private ShapeRenderer shapeRenderer;
    private Color color;

    public CharacterViewDebug() {
        this.shapeRenderer = new ShapeRenderer();
        float rand = MathUtils.random();
        color = new Color(1, rand, rand, rand);
    }

    public void draw(Character character, Batch batch) {
        if (character.isMoving()) {
            Movement[] movements = character.getMovements();

            batch.end();

            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

            shapeRenderer.setColor(color);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.line(character.getX() + character.getOriginX(), character.getY() + character.getOriginY(),
                    movements[0].destination.x,
                    movements[0].destination.y);
            shapeRenderer.end();

            shapeRenderer.setColor(1f, 0f, 0f, 1);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(movements[0].destination.x, movements[0].destination.y, 3);
            shapeRenderer.end();

            for (int i = 1; i < movements.length; i++) {
                shapeRenderer.setColor(color);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.line(movements[i-1].destination.x, movements[i-1].destination.y,
                        movements[i].destination.x,
                        movements[i].destination.y);
                shapeRenderer.end();

                shapeRenderer.setColor(1f, 0f, 0f, 0.5f);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.circle(movements[i].destination.x, movements[i].destination.y, 3);
                shapeRenderer.end();
            }

            batch.begin();
        }

    }
}
