package com.kiriost.game.gameobject;

import com.badlogic.gdx.utils.Array;
import com.kiriost.game.gameobject.character.Npc;
import com.kiriost.game.gameobject.character.Player;
import com.kiriost.game.gameobject.map.ITerrainObserver;
import com.kiriost.game.gameobject.map.Terrain;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.IUpdatable;

import java.util.Iterator;

/**
 * Created by kiriost on 13/04/16.
 */
public class WorldManager implements ITerrainObserver, IUpdatable {
    private static WorldManager instance;

    private Terrain terrain;

    private Array<Player> players;

    private Array<Npc> npcs;

    private WorldManager() {
        players = new Array<Player>();
        npcs = new Array<Npc>();
    }

    public static WorldManager getInstance() {
        if (instance == null) {
            instance = new WorldManager();
        }
        return instance;
    }

    public Terrain createTerrain() {
        terrain = new Terrain();
        terrain.subscribe(this);
        return terrain;
    }

    public Player createPlayer(String type) {
        Player player = new Player("civilian");
        players.add(player);
        return player;
    }

    public Npc createNpc(String type) {
        Npc npc = new Npc("zombie");
        npcs.add(npc);
        return npc;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        if (!ModifierKey.L_SHIFT) {
            for (int i = 0; i < players.size; i++) {
                Player player = players.get(i);
                if (player != selectedPlayer) {
                    player.deselect();
                }
            }
        }
    }

    public Iterator<Player> playerIterator() {
        return players.iterator();
    }

    public Iterator<Npc> npcIterator() {
        return npcs.iterator();
    }

    @Override
    public void terrainClicked(float x, float y) {
        for (int i = 0; i < players.size; i++) {
            Player player = players.get(i);
            if (player.isSelected())
                players.get(i).move(x, y);
        }
    }

    @Override
    public void update(float delta) {

    }
}
