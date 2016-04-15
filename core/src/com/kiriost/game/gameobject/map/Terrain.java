package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.manager.ConfigManager;

/**
 * Created by kiriost on 02/04/16.
 */
public class Terrain extends Actor {
    private final int terrainRows = 128;
    private final int terrainCols = 128;
    private final int square = ConfigManager.getInstance().getIntProperty("square");

    private final TerrainView terrainView;
    private TerrainViewDebug terrainViewDebug;

    private Array<ITerrainObserver> terrainObservers;

    public Terrain() {
        terrainView = new TerrainView();

        terrainObservers = new Array<ITerrainObserver>();

        setX(0f);
        setY(0f);
        setWidth(terrainRows * square);
        setHeight(terrainCols * square);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < terrainObservers.size; i++) {
                    terrainObservers.get(i).terrainClicked(x, y);
                }
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        terrainView.draw(this, batch, parentAlpha);
    }

    public void subscribe(ITerrainObserver observer) {
        if (!terrainObservers.contains(observer, true))
            terrainObservers.add(observer);
    }

    public boolean unsubscribe(ITerrainObserver observer) {
        return terrainObservers.removeValue(observer, true);
    }
}
