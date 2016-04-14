package com.kiriost.game.util;

import com.kiriost.game.manager.ConfigManager;

/**
 * Created by kiriost on 09/04/16.
 */
public class Grid {
    public final static int square = ConfigManager.getInstance().getIntProperty("square");

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
