package com.kiriost.game.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by kiriost on 02/04/16.
 */
public class Terrain extends Actor {
    private int terrainRows = 64;
    private int terrainCols = 64;
    private int square = 8;

    public Terrain() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        setColor(color.r, color.g, color.b, color.a * parentAlpha);


    }
}
