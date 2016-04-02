package com.kiriost.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kiriost.game.AppGame;
import com.kiriost.game.actor.Terrain;
import com.kiriost.game.debug.actor.Ghost;
import com.kiriost.game.debug.actor.Grid;

/**
 * Created by kiriost on 29/03/16.
 */
public class GameScreen extends ScreenAdapter {
    private AppGame game;
    private Stage stage;
    private Actor debugGrid, terrain, ghost;

    public GameScreen(AppGame game) {
        stage = new Stage(new ScreenViewport());

        terrain = new Terrain();
        ghost = new Ghost();
        debugGrid = new Grid();

        stage.addActor(terrain);
        stage.addActor(ghost);
        stage.addActor(debugGrid);

        stage.setKeyboardFocus(debugGrid);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            stage.getCamera().translate(-8f, 0f, 0f);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            stage.getCamera().translate(8f, 0f, 0f);
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            stage.getCamera().translate(0f, -8f, 0f);
        else if (Gdx.input.isKeyPressed(Input.Keys.UP))
            stage.getCamera().translate(0f, 8f, 0f);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
