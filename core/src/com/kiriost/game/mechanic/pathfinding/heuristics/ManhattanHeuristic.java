package com.kiriost.game.mechanic.pathfinding.heuristics;

import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.map.TileBasedMap;
import com.kiriost.game.mechanic.pathfinding.AStarHeuristic;

/**
 * A heuristic that drives the search based on the Manhattan distance
 * between the current location and the target
 *
 * @author Kevin Glass
 */
public class ManhattanHeuristic implements AStarHeuristic {
    /**
     * The minimum movement cost from any one square to the next
     */
    private int minimumCost;

    /**
     * Create a new heuristic
     *
     * @param minimumCost The minimum movement cost from any one square to the next
     */
    public ManhattanHeuristic(int minimumCost) {
        this.minimumCost = minimumCost;
    }

    /**
     * @see AStarHeuristic#getCost(TileBasedMap, GameObject, int, int, int, int)
     */
    public float getCost(TileBasedMap map, GameObject mover, int x, int y, int tx,
                         int ty) {
        return minimumCost * (Math.abs(x - tx) + Math.abs(y - ty));
    }

}
