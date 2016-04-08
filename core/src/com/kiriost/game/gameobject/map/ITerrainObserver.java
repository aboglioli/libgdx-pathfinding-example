package com.kiriost.game.gameobject.map;

/**
 * Created by kiriost on 08/04/16.
 */
public interface ITerrainObserver {
    public void setTerrain(Terrain terrain);

    public void terrainClicked(float x, float y);
}
