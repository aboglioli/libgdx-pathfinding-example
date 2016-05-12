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
import com.kiriost.game.screen.CameraManager;

/**
 * Created by kiriost on 05/04/16.
 */
public class WorldManager extends Stage implements IMapObserver {
    private Array<GameObject> gameObjects;

    private CameraManager cameraManager;
    private Map map;

    public WorldManager() {
        super(new ScreenViewport());

        gameObjects = new Array<GameObject>();

        createWorld();
    }

    private void createWorld() {
        // Map
        map = new Map();
        map.subscribe(this);

        addActor(map);

        // Character
        NpcView npcView = new NpcView();
        for (int i = 0; i < 3; i++) {
            GameObject zombie = new Npc(npcView);
            zombie.setPosition(100, 100 * i);
            gameObjects.add(zombie);

            addActor(zombie);
        }

        PlayerView playerView = new PlayerView();
        for (int i = 0; i < 4; i++) {
            GameObject player = new Player(playerView);
            player.setPosition(i * 200, i * 200);
            gameObjects.add(player);

            addActor(player);
        }

        // Camera
        cameraManager = new CameraManager(getCamera(), map.getWidth(), map.getHeight());
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        if (!ModifierKey.L_SHIFT) {
            for (int i = 0; i < gameObjects.size; i++) {
                GameObject player = gameObjects.get(i);
                if (player != selectedPlayer) {
                    player.setStatus("select", false);
                }
            }
        }
    }

    public void update() {
        act();
        draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        cameraManager.update(delta);
    }

    @Override
    public void terrainClicked(float x, float y) {
        for (int i = 0; i < gameObjects.size; i++) {
            GameObject player = gameObjects.get(i);
            if (player.getStatus("select")) {
//                gameObjects.get(i).move(x, y);
            }
        }
    }
}
