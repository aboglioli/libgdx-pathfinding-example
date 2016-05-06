package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiriost.game.input.ModifierKey;

/**
 * Created by kiriost on 13/04/16.
 */
public class MapView {
    private MapViewDebug mapViewDebug;

    private int square;

    private Texture texture;
    private TextureRegion tiles[][];

    public MapView() {
        mapViewDebug = new MapViewDebug();

        texture = new Texture("map/terrain_tiles.png");
        tiles = TextureRegion.split(texture, 144, 144);

        square = 64;
    }

    public void draw(Map map, Batch batch, float parentAlpha) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Color color = map.getColor();
        map.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        for (int i = 0; i < map.getWidth(); i += square) {
            for (int j = 0; j < map.getHeight(); j += square) {
                batch.draw(tiles[0][1],
                        map.getX() + i, map.getY() + j, map.getOriginX(),
                        map.getOriginY(), square, square,
                        map.getScaleX(), map.getScaleY(), map.getRotation());
            }
        }

        if (ModifierKey.F12)
            mapViewDebug.draw(map, batch);
    }
}
