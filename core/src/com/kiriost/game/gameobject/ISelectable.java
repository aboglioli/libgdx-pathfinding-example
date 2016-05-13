package com.kiriost.game.gameobject;

import com.kiriost.game.mechanic.pathfinding.PathFinder;

/**
 * Created by kiriost on 13/05/16.
 */
public interface ISelectable {
    void setSelectedObjectContainer(SelectedObjectContainer selectedObjectContainer);
    void deselected();
    void move(PathFinder pathFinder, int x, int y);
}
