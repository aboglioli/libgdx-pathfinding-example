package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.mechanic.Grid;

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
                for (IMapObserver observer : terrainObservers) {
                    observer.terrainClicked((int)x, (int)y);
                }
                return true;
            }
        });
    }

    // Actor
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

    // TileBaseMap
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
    public boolean blocked(GameObject mover, int x, int y) {
        return false;
    }

    @Override
    public float getCost(GameObject mover, int sx, int sy, int tx, int ty) {
        return 1;
    }
}
