package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.mechanic.Grid;
import com.kiriost.game.mechanic.pathfinding.Mover;

/**
 * Created by kiriost on 02/04/16.
 */
public class Map extends Actor implements TileBasedMap {
    private final int rows = 128;
    private final int cols = 128;
    private final int square = Grid.square;

    private final MapView mapView;

    private Array<IMapObserver> terrainObservers;

    public Map() {
        mapView = new MapView();

        terrainObservers = new Array<IMapObserver>();

        setX(0f);
        setY(0f);
        setWidth(rows * square);
        setHeight(cols * square);

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
        mapView.draw(this, batch, parentAlpha);
    }

    public void subscribe(IMapObserver observer) {
        if (!terrainObservers.contains(observer, true))
            terrainObservers.add(observer);
    }

    public boolean unsubscribe(IMapObserver observer) {
        return terrainObservers.removeValue(observer, true);
    }

    @Override
    public int getWidthInTiles() {
        return rows;
    }

    @Override
    public int getHeightInTiles() {
        return cols;
    }

    @Override
    public void pathFinderVisited(int x, int y) {
    }

    @Override
    public boolean blocked(Mover mover, int x, int y) {
        return false;
    }

    @Override
    public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
        return 1;
    }
}
