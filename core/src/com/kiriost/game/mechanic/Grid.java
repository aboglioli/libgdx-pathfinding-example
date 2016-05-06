package com.kiriost.game.mechanic;

/**
 * Created by kiriost on 09/04/16.
 */
public class Grid {
    public final static int square = 32;

    public static int pixelsToUnits(float x) {
        return (int) x / square;
    }

    public static float unitsToPixels(int unit) {
        return unit * square;
    }

    public static float normalize(float x) {
        return unitsToPixels(pixelsToUnits(x));
    }
}
