package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.GameView;
import com.kiriost.game.gameobject.ISelectable;
import com.kiriost.game.gameobject.SelectedObjectContainer;
import com.kiriost.game.input.ModifierKey;
import com.kiriost.game.mechanic.Grid;
import com.kiriost.game.mechanic.pathfinding.Path;
import com.kiriost.game.mechanic.pathfinding.PathFinder;

/**
 * Created by kiriost on 08/04/16.
 */
public class Player extends GameObject implements ISelectable {
    private SelectedObjectContainer selectedObjectContainer;
    private Path path = null;
    private int pathCounter = 0;

    public Player(GameView view) {
        super(view);

        setStatus("moving", false);

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setStatus("selected", true);
                return true;
            }
        });
    }

    public void updatePosition(float delta) {
        if (path != null) {
            Path.Step step = path.getStep(pathCounter++);
            setPosition(Grid.pixels(step.getX()), Grid.pixels(step.getY()));
            if (path.getLength() == pathCounter) {
                path = null;
                pathCounter = 0;
                movementFinished();
            }
        }
    }

    // GameObject
    @Override
    protected void update(float delta) {
        updatePosition(delta);
    }

    @Override
    protected void statusChanged(String name, boolean status, boolean changed) {
        if (name.equals("selected") && status) {
            selectedObjectContainer.set(this);
        } else if (name.equals("moving") && changed) {
            resetDuration();
        }
    }

    @Override
    protected void movementStarted() {
        setStatus("moving", true);
    }

    @Override
    protected void movementFinished() {
        setStatus("moving", false);
    }


    // ISelectable
    @Override
    public void setSelectedObjectContainer(SelectedObjectContainer selectedObjectContainer) {
        this.selectedObjectContainer = selectedObjectContainer;
    }

    @Override
    public void deselected() {
        setStatus("selected", false);
    }

    @Override
    public void move(PathFinder pathFinder, int x, int y) {
        path = pathFinder.findPath(this, Grid.units(getX()), Grid.units(getY()), Grid.units(x), Grid.units(y));
        pathCounter = 0;

//        if (path != null) {
//            for (int i = 0; i < path.getLength(); i++) {
//                Path.Step step = path.getStep(i);
//                System.out.println(step.getX() + " -- " + step.getY());
//                System.out.println("( " + Grid.pixels(step.getX()) + " -- " + Grid.pixels(step.getY()) + " )");
//            }
//        }

        if (getStatus("moving") && ModifierKey.L_SHIFT) {

        } else {
            if (getStatus("moving")) {

            }

            setStatus("moving", true);

            movementStarted();
        }
    }
}