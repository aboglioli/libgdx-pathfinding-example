package com.kiriost.game.manager;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kiriost.game.gameobject.character.Npc;
import com.kiriost.game.gameobject.character.Player;
import com.kiriost.game.gameobject.map.Terrain;

/**
 * Created by kiriost on 05/04/16.
 */
public class LoadManager implements Disposable {
    private static LoadManager instance;
    private final Stage stage;
    private Terrain terrain;

    private LoadManager() {
        stage = new Stage(new ScreenViewport());
//        stage.getBatch().disableBlending();

    }

    public static LoadManager getInstance() {
        if (instance == null)
            instance = new LoadManager();
        return instance;
    }

    public Stage getStage() {
        loadMap();
        loadResources();
        loadBuildings();
        loadNpcs();
        loadCharacters();

        return stage;
    }

    private void loadMap() {
        terrain = new Terrain();
        stage.addActor(terrain);
    }

    private void loadCharacters() {
        for (int i = 0; i < 4; i++) {
            Player player = new Player();
            player.setTerrain(terrain);
            player.setPosition(i * 200, i * 200);
            stage.addActor(player);
        }
    }

    private void loadNpcs() {
        for (int i = 0; i < 3; i++) {
            Actor zombie = new Npc();
            zombie.setPosition(MathUtils.random(500), MathUtils.random(500));
            stage.addActor(zombie);
        }
    }

    private void loadBuildings() {

    }

    private void loadResources() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
