package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiriost.game.manager.ConfigManager;

/**
 * Created by kiriost on 13/04/16.
 */
public class TerrainView {
    private boolean debug = false;
    private TerrainViewDebug terrainViewDebug;

    private int square;

    private Texture texture;
    private TextureRegion tiles[][];

    public TerrainView() {
        debug = ConfigManager.getInstance().getBooleanProperty("debug");
        terrainViewDebug = new TerrainViewDebug();

        texture = new Texture("map/terrain_tiles.png");
        tiles = TextureRegion.split(texture, 144, 144);
        System.out.printf(tiles.length + " -- " + tiles[0].length);

        square = 64;
    }

    public void draw(Terrain terrain, Batch batch, float parentAlpha) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Color color = terrain.getColor();
        terrain.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        for (int i = 0; i < terrain.getWidth(); i += square) {
            for (int j = 0; j < terrain.getHeight(); j += square) {
                batch.draw(tiles[0][1],
                        terrain.getX() + i, terrain.getY() + j, terrain.getOriginX(),
                        terrain.getOriginY(), square, square,
                        terrain.getScaleX(), terrain.getScaleY(), terrain.getRotation());
            }
        }

        if (debug)
            terrainViewDebug.draw(terrain, batch);
    }
}
