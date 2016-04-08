package com.kiriost.game.gameobject.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.util.Grid;

/**
 * Created by kiriost on 02/04/16.
 */
public class Terrain extends Actor {
    private final int terrainRows = 128;
    private final int terrainCols = 128;
    private final int square = Grid.square;
    private final boolean DEBUG = true;

    private Array<ITerrainObserver> terrainObservers;

    private Texture texture;
    private TextureRegion textureRegion;

    private TerrainGrid terrainGrid;

    public Terrain() {
        texture = new Texture("map/terrain_tiles.png");
        textureRegion = new TextureRegion(texture, 0, 0, 143, 143);

        terrainObservers = new Array<ITerrainObserver>();

        terrainGrid = new TerrainGrid();

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
        Color color = getColor();
        setColor(color.r, color.g, color.b, color.a * parentAlpha);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int width = terrainRows * square;
        int height = terrainCols * square;
        for (int i = 0; i < width; i += square) {
            for (int j = 0; j < height; j += square) {
                batch.draw(textureRegion, getX() + i, getY() + j, getOriginX(), getOriginY(), square, square,
                        getScaleX(), getScaleY(), getRotation());
            }
        }

        if (DEBUG)
            terrainGrid.draw(batch, getX(), getY(), getWidth(), getHeight(), square);
    }

    public void subscribe(ITerrainObserver observer) {
        if (!terrainObservers.contains(observer, true))
            terrainObservers.add(observer);
    }

    public boolean unsubscribe(ITerrainObserver observer) {
        return terrainObservers.removeValue(observer, true);
    }
}
