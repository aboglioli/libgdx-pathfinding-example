package com.kiriost.game.mechanic.pathfinding.heuristics;

import com.kiriost.game.gameobject.map.TileBasedMap;
import com.kiriost.game.mechanic.pathfinding.AStarHeuristic;
import com.kiriost.game.mechanic.pathfinding.Mover;

/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile. In this case the sqrt is removed
 * and the distance squared is used instead
 *
 * @author Kevin Glass
 */
public class ClosestSquaredHeuristic implements AStarHeuristic {

    /**
     * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
     */
    public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty) {
        float dx = tx - x;
        float dy = ty - y;

        return ((dx * dx) + (dy * dy));
    }

}
