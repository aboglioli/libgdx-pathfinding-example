package com.kiriost.game.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by kiriost on 10/04/16.
 */
public class GridVector extends Vector2 {
    private int row = 0;
    private int col = 0;

    public GridVector() {
        super();
        this.setX(0);
        this.setY(0);
    }

    public GridVector(float x, float y) {
        super();
        this.setX(x);
        this.setY(y);
    }

    public void setX(float x) {
        this.row = Grid.pixelsToUnits(x);
        this.x = Grid.unitsToPixels(this.row);
    }

    public void setY(float y) {
        this.col = Grid.pixelsToUnits(y);
        this.y = Grid.unitsToPixels(this.col);
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
        this.x = Grid.unitsToPixels(row);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
        this.y = Grid.unitsToPixels(col);
    }
}
