package com.kiriost.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kiriost.game.AppGame;

/**
 * Created by kiriost on 29/03/16.
 */
public class GameScreen implements Screen {
    private AppGame game;
    private OrthographicCamera camera;

    private ShapeRenderer shapeRenderer;

    private int width, height;
    final private int whSquare = 8;

    public GameScreen(AppGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);

        // Grass
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f, 0.6f, 0f, 1f);

        for (int i = 0; i <= this.width; i += whSquare) {
            for (int j = 0; j < this.height; j += whSquare) {
                shapeRenderer.rect(i, j, whSquare, whSquare);
            }
        }

        shapeRenderer.end();

        // Characters
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f, 0f, 0f, 1f);

        for (int i = 0; i <= this.width; i += whSquare) {
            for (int j = 0; j < this.height; j += whSquare) {
                if (Math.random() < 0.02) {
                    shapeRenderer.circle(i, j, whSquare / 2);
                }
            }
        }

        shapeRenderer.end();

        // Grid
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.5f, 0f, 0f, 0.5f);

        for (int i = 0; i <= this.width; i += whSquare) {
            shapeRenderer.line(i, 0, i, this.height);
        }


        for (int i = 0; i <= this.height; i += whSquare) {
            shapeRenderer.line(0, i, this.width, i);
        }

        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
