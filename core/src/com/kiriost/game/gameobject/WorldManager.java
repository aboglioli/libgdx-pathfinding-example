package com.kiriost.game.gameobject;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kiriost.game.gameobject.character.Npc;
import com.kiriost.game.gameobject.character.NpcView;
import com.kiriost.game.gameobject.character.Player;
import com.kiriost.game.gameobject.character.PlayerView;
import com.kiriost.game.gameobject.map.IMapObserver;
import com.kiriost.game.gameobject.map.Map;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.pathfinding.AStarPathFinder;
import com.kiriost.game.mechanic.pathfinding.PathFinder;
import com.kiriost.game.screen.CameraManager;

/**
 * Created by kiriost on 05/04/16.
 */
public class WorldManager extends Stage implements IMapObserver {
    private Array<GameObject> gameObjects;
    private SelectedObjectContainer selectedObjects;

    private CameraManager cameraManager;
    private Map map;
    private PathFinder pathFinder;

    public WorldManager() {
        super(new ScreenViewport());

        gameObjects = new Array<GameObject>();
        selectedObjects = new SelectedObjectContainer();

        createWorld();
    }

    private void createWorld() {
        // Map
        map = new Map();
        pathFinder = new AStarPathFinder(map);
        map.subscribe(this);

        addActor(map);

        // Character
        NpcView npcView = new NpcView();
        for (int i = 0; i < 3; i++) {
            Npc zombie = new Npc(npcView);
            zombie.setPosition(100, 100 * i);
            gameObjects.add(zombie);

            addActor(zombie);
        }

        PlayerView playerView = new PlayerView();
        for (int i = 0; i < 4; i++) {
            Player player = new Player(playerView);
            player.setPosition(i * 200, i * 200);
            player.setSelectedObjectContainer(selectedObjects);
            gameObjects.add(player);

            addActor(player);
        }

        // Camera
        cameraManager = new CameraManager(getCamera(), map.getWidth(), map.getHeight());
    }

    public void update(float delta) {
        act(delta);
        cameraManager.update(delta);
        draw();
    }

    @Override
    public void terrainClicked(int x, int y) {
        selectedObjects.move(pathFinder, x, y);
    }
}
