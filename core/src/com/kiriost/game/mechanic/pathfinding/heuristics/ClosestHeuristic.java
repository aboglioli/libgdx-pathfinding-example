package com.kiriost.game.mechanic.pathfinding.heuristics;

import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.map.TileBasedMap;
import com.kiriost.game.mechanic.pathfinding.AStarHeuristic;

/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 *
 * @author Kevin Glass
 */
public class ClosestHeuristic implements AStarHeuristic {
    /**
     * @see AStarHeuristic#getCost(TileBasedMap, GameObject, int, int, int, int)
     */
    public float getCost(TileBasedMap map, GameObject mover, int x, int y, int tx, int ty) {
        float dx = tx - x;
        float dy = ty - y;

        float result = (float) (Math.sqrt((dx * dx) + (dy * dy)));

        return result;
    }

}
