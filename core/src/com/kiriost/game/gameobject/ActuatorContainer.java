package com.kiriost.game.gameobject;

import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.pathfinding.PathFinder;

/**
 * Created by kiriost on 13/05/16.
 */
public class ActuatorContainer {
    private Array<IActuator> actuators;

    public ActuatorContainer() {
        this.actuators = new Array<IActuator>();
    }

    public void subscribe(IActuator actuator) {
        if(!actuators.contains(actuator, true)) {
            actuators.add(actuator);
        }
    }

    public void notify(String event, IActuator actuator) {
        if(event.equals("selected")) {
            if (!ModifierKey.L_SHIFT) {
                for (IActuator act : actuators) {
                    if(act.isSelectable() && act.isSelected() && actuator != act) {
                        act.deselect();
                    }
                }

            }
        }

    }

    public void move(PathFinder pathFinder, int x, int y) {
        for (IActuator actuator : actuators) {
            if(actuator.isSelectable() && actuator.isSelected() && actuator.isMovable()) {
                actuator.move(pathFinder, x, y);
            }
        }
    }
}
