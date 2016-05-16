package com.kiriost.game.gameobject.character;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kiriost.game.gameobject.ActuatorContainer;
import com.kiriost.game.gameobject.GameObject;
import com.kiriost.game.gameobject.GameView;
import com.kiriost.game.gameobject.IActuator;
import com.kiriost.game.mechanic.Grid;
import com.kiriost.game.mechanic.movement.Movement;
import com.kiriost.game.mechanic.pathfinding.Path;
import com.kiriost.game.mechanic.pathfinding.PathFinder;

/**
 * Created by kiriost on 08/04/16.
 */
public class Player extends GameObject implements IActuator {
    private ActuatorContainer actuatorContainer;
    private Movement movement;

    public Player(GameView view) {
        super(view);

        setStatus("moving", false);

        movement = new Movement();

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                select();
                return true;
            }
        });
    }

    public void updatePosition(float delta) {
        if (movement.hasNextStep()) {
            Vector2 pos = movement.nextStep();

            addPosition(pos.x, pos.y);
            setRotation(pos.angle() + 90);
            if(!movement.hasNextStep()) {
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
    protected void statusChanged(String name, boolean status, boolean oldStatus) {
        if (name.equals("moving") && status != oldStatus) {
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
        setPosition(Math.round(getX()), Math.round(getY()));
    }

    // IActuator
    @Override
    public void setActuatorContainer(ActuatorContainer actuatorContainer) {
        this.actuatorContainer = actuatorContainer;
        this.actuatorContainer.subscribe(this);
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public boolean isSelected() {
        return getStatus("selected");
    }

    @Override
    public void select() {
        setStatus("selected", true);
        actuatorContainer.notify("selected", this);
    }

    @Override
    public void deselect() {
        setStatus("selected", false);
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public void move(PathFinder pathFinder, int x, int y) {
        if(movement.hasNextStep()) {
            Vector2 absolutePath = movement.nextAbsoluteStep();

            Path path = pathFinder.findPath(this, Grid.units(absolutePath.x), Grid.units(absolutePath.y),
                    Grid.units(x), Grid.units(y));
            if (path != null && path.getLength() > 0) {
                movement.setPath(path);
            }
        }
        else {
            System.out.println("Src: " + getX() + " - " + getY());
            Path path = pathFinder.findPath(this, Grid.units(getX()), Grid.units(getY()), Grid.units(x), Grid.units(y));
            if (path != null && path.getLength() > 0) {
                movement.setPath(path);
                movementStarted();
            }
        }
    }

}