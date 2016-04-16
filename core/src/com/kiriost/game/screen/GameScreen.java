package com.kiriost.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kiriost.game.AppGame;
import com.kiriost.game.gameobject.StageManager;
import com.kiriost.game.input.ModifierKey;

/**
 * Created by kiriost on 29/03/16.
 */
public class GameScreen extends ScreenAdapter {
    private AppGame game;

    private StageManager stageManager;
    private Viewport viewport;

    public GameScreen(AppGame game) {
        this.game = game;

        // Load game objects
        stageManager = StageManager.getInstance();
        stageManager.load();
        viewport = stageManager.getViewport();

        // InputProcessors
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stageManager);
        inputMultiplexer.addProcessor(new ModifierKey());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stageManager.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        stageManager.dispose();
    }
}