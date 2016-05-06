package com.kiriost.game.gameobject;

import com.badlogic.gdx.utils.Array;
import com.kiriost.game.gameobject.character.Npc;
import com.kiriost.game.gameobject.character.Player;
import com.kiriost.game.gameobject.map.IMapObserver;
import com.kiriost.game.gameobject.map.Map;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.IUpdatable;

import java.util.Iterator;

/**
 * Created by kiriost on 13/04/16.
 */
public class WorldManager implements IMapObserver, IUpdatable {
    private static WorldManager instance;

    private Map map;

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

    public Map createTerrain() {
        map = new Map();
        map.subscribe(this);
        return map;
    }

    public Player createPlayer(String type) {
        Player player = new Player();
        players.add(player);
        return player;
    }

    public Npc createNpc(String type) {
        Npc npc = new Npc();
        npcs.add(npc);
        return npc;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        if (!ModifierKey.L_SHIFT) {
            for (int i = 0; i < players.size; i++) {
                Player player = players.get(i);
                if (player != selectedPlayer) {
                    player.setStatus("select", false);
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
            if (player.getStatus("select"))
                players.get(i).move(x, y);
        }
    }

    @Override
    public void update(float delta) {

    }
}
