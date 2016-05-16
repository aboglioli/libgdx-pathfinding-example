package com.kiriost.game.gameobject;

import com.kiriost.game.mechanic.pathfinding.PathFinder;

public interface IActuator {
    void setActuatorContainer(ActuatorContainer actuatorContainer);

    boolean isSelectable();
    boolean isSelected();
    void select();
    void deselect();

    boolean isMovable();
    void move(PathFinder pathFinder, int x, int y);
}
