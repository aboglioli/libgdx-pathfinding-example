package com.kiriost.game.gameobject;

import com.badlogic.gdx.utils.Array;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.pathfinding.PathFinder;

/**
 * Created by kiriost on 13/05/16.
 */
public class SelectedObjectContainer {
    private Array<ISelectable> selectedObjects;

    public SelectedObjectContainer() {
        this.selectedObjects = new Array<ISelectable>();
    }

    public void set(ISelectable selectable) {
        if(!ModifierKey.L_SHIFT) {
            for (ISelectable toDeselect : selectedObjects)
                toDeselect.deselected();
            selectedObjects.clear();
        }

        selectedObjects.add(selectable);
    }

    public void move(PathFinder pathFinder, int x, int y) {
        for (ISelectable selectable : selectedObjects) {
            selectable.move(pathFinder, x, y);
        }
    }
}
