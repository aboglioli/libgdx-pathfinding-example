package com.kiriost.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kiriost.game.AppGame;
import com.kiriost.game.gameobject.character.Civilian;
import com.kiriost.game.gameobject.map.Terrain;
import com.kiriost.game.gameobject.npc.Zombie;

/**
 * Created by kiriost on 29/03/16.
 */
public class GameScreen extends ScreenAdapter {
    private AppGame game;
    private Stage stage;

    public GameScreen(AppGame game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());

        Actor terrain = new Terrain();
        stage.addActor(terrain);

        for (int i = 0; i < 4; i++) {
            Actor civilian = new Civilian();
            civilian.setPosition(MathUtils.random(200), MathUtils.random(200));
            stage.addActor(civilian);
        }

        for (int i = 0; i < 3; i++) {
            Actor zombie = new Zombie();
            zombie.setPosition(MathUtils.random(500), MathUtils.random(500));
            stage.addActor(zombie);
        }

        // InputProcessors
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}