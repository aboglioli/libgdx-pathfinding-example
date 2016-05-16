package com.kiriost.game.mechanic;

/**
 * Created by kiriost on 09/04/16.
 */
public class Grid {
    public final static int square = 8;

    public static int units(float x) {
        return (int) x / square;
    }

    public static float pixels(int unit) {
        return unit * square;
    }

    public static float normalize(float x) {
        return pixels(units(x));
    }
}
