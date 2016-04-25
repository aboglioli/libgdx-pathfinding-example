package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.kiriost.game.mechanic.Movement;

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

        if (character.getStatus("move")) {
            Movement[] movements = character.getPath();

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
