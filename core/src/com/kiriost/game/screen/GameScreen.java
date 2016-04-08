package com.kiriost.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kiriost.game.AppGame;
import com.kiriost.game.manager.LoadManager;

/**
 * Created by kiriost on 29/03/16.
 */
public class GameScreen extends ScreenAdapter {
    private AppGame game;
    private LoadManager loader;

    private Stage stage;
    private Camera camera;
    private Viewport viewport;

    private int cameraTranslateModifier = 1;

    public GameScreen(AppGame game) {
        this.game = game;

        // Load game objects
        loader = LoadManager.getInstance();
        stage = loader.getStage();
        camera = stage.getCamera();
        viewport = stage.getViewport();

        // InputProcessors
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();

        // Camera movements
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            cameraTranslateModifier = 4;
        else
            cameraTranslateModifier = 1;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && camera.position.x >= 0) {
            camera.translate(-8f * cameraTranslateModifier, 0f, 0f);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && camera.position.x <= 1024) {
            camera.translate(8f * cameraTranslateModifier, 0f, 0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && camera.position.y >= 0) {
            camera.translate(0f, -8f * cameraTranslateModifier, 0f);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && camera.position.y <= 1024) {
            camera.translate(0f, 8f * cameraTranslateModifier, 0f);
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        loader.dispose();
    }
}