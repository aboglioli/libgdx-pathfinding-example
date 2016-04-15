package com.kiriost.game.gameobject.character.basic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

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
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(character.getX(), character.getY(), character.getWidth(), character.getHeight());
        shapeRenderer.end();

        shapeRenderer.setColor(Color.GOLDENROD);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(character.getLimits().x, character.getLimits().y, character.getLimits().width,
                character.getLimits().height);
        shapeRenderer.end();

        if (character.isMoving()) {
            Movement[] movements = character.getMovements();

            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.line(character.getX() + character.getOriginX(), character.getY() + character.getOriginY(),
                    movements[0].getDestination().x,
                    movements[0].getDestination().y);
            shapeRenderer.end();

            shapeRenderer.setColor(1f, 0f, 0f, 1);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(movements[0].getDestination().x, movements[0].getDestination().y, 3);
            shapeRenderer.end();

            for (int i = 1; i < movements.length; i++) {
                shapeRenderer.setColor(color);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.line(movements[i - 1].getDestination().x, movements[i - 1].getDestination().y,
                        movements[i].getDestination().x,
                        movements[i].getDestination().y);
                shapeRenderer.end();

                shapeRenderer.setColor(Color.RED);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.circle(movements[i].getDestination().x, movements[i].getDestination().y, 3);
                shapeRenderer.end();
            }

        }

        batch.begin();
    }
}
