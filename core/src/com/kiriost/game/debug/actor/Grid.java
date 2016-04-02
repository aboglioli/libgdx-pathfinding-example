package com.kiriost.game.debug.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by kiriost on 02/04/16.
 */
public class Grid extends Actor {
    private boolean showGrid;
    private ShapeRenderer shapeRenderer;
    private int square = 8;

    public Grid() {
        showGrid = false;
        shapeRenderer = new ShapeRenderer();

        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.F2) {
                    System.out.println(showGrid);
                    showGrid = !showGrid;
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(showGrid) {
            batch.end();

            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
            shapeRenderer.setColor(0.5f, 0f, 0f, 1f);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            int width = Gdx.graphics.getWidth();
            int height = Gdx.graphics.getHeight();
            for (int i = 0; i < width; i += square) {
                shapeRenderer.line(i, 0, i, height);
            }
            for (int i = 0; i < height; i += square) {
                shapeRenderer.line(0, i, width, i);
            }
            shapeRenderer.end();

            batch.begin();
        }
    }
}
