package com.kiriost.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kiriost.game.AppGame;
import com.kiriost.game.actor.Civilian;
import com.kiriost.game.actor.Terrain;
import com.kiriost.game.actor.Zombie;

/**
 * Created by kiriost on 29/03/16.
 */
public class GameScreen extends ScreenAdapter {
    private AppGame game;
    private Stage stage;
    private Actor terrain, civilian, zombie;

    public GameScreen(AppGame game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());

        terrain = new Terrain();
        civilian = new Civilian();
        zombie = new Zombie();
        zombie.setPosition(150f, 150f);

        // Terrain
        stage.addActor(terrain);

        // Resources

        // Actors
        stage.addActor(civilian);
        stage.addActor(zombie);

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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
