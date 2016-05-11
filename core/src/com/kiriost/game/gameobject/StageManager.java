package com.kiriost.game.gameobject;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kiriost.game.gameobject.character.Player;
import com.kiriost.game.gameobject.map.Map;
import com.kiriost.game.mechanic.IUpdatable;
import com.kiriost.game.screen.CameraManager;

/**
 * Created by kiriost on 05/04/16.
 */
public class StageManager extends Stage {
    private static StageManager instance;
    private Array<IUpdatable> updatables;

    private WorldManager worldManager;

    private StageManager() {
        super(new ScreenViewport());

        updatables = new Array<IUpdatable>();
        worldManager = WorldManager.getInstance();
        updatables.add(worldManager);
    }

    public static StageManager getInstance() {
        if (instance == null)
            instance = new StageManager();
        return instance;
    }

    public void load() {
        loadMap();
        loadResources();
        loadBuildings();
        loadItems();
        loadNpcs();
        loadCharacters();
    }

    public void update() {
        act();
        draw();
    }

    private void loadMap() {
        Map map = worldManager.createTerrain();
        addActor(map);
        updatables.add(new CameraManager(getCamera(), map.getWidth(), map.getHeight()));
    }

    private void loadResources() {

    }

    private void loadBuildings() {

    }

    private void loadItems() {

    }

    private void loadNpcs() {
        for (int i = 0; i < 3; i++) {
            Actor zombie = worldManager.createNpc("zombie");
            zombie.setPosition(100, 100 * i);
            addActor(zombie);
        }
    }

    private void loadCharacters() {
        for (int i = 0; i < 4; i++) {
            Player player = worldManager.createPlayer("civilian");
            player.setPosition(i * 200, i * 200);
            addActor(player);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (int i = 0; i < updatables.size; i++) {
            IUpdatable updatable = updatables.get(i);
            updatable.update(delta);
        }
    }
}
