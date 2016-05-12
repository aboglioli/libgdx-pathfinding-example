package com.kiriost.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kiriost.game.AppGame;
import com.kiriost.game.gameobject.WorldManager;
import com.kiriost.game.input.ModifierKey;

/**
 * Created by kiriost on 29/03/16.
 */
public class GameScreen extends ScreenAdapter {
    private AppGame game;

    private WorldManager worldManager;
    private Viewport viewport;

    public GameScreen(AppGame game) {
        this.game = game;

        // Load game objects
        worldManager = new WorldManager();
        viewport = worldManager.getViewport();

        // InputProcessors
        InputMultiplexer inputMultiplexer = new InputMultiplexer(worldManager);
        inputMultiplexer.addProcessor(new ModifierKey());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        worldManager.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        worldManager.dispose();
    }
}