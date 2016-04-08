package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.gameobject.map.ITerrainObserver;
import com.kiriost.game.gameobject.map.Terrain;

/**
 * Created by kiriost on 08/04/16.
 */
public class Player extends Character implements ITerrainObserver {
    private static Player selected;
    private Terrain terrain;

    public Player() {
        super(new PlayerView());
        setStatus("idle");

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setStatus("selected");
                return true;
            }
        });
    }

    @Override
    protected void update(float delta) {

    }

    @Override
    protected void statusChanged() {
        if (getStatus().equals("selected")) {
            if (selected != null) {
                selected.setStatus("idle");
                terrain.unsubscribe(selected);
            }
            selected = this;
            terrain.subscribe(this);
        }
    }

    @Override
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public void terrainClicked(float x, float y) {
        if (getStatus().equals("selected") || getStatus().equals("walk")) {
            move(x, y);
        }
    }
}
