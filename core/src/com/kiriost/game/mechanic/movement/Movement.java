package com.kiriost.game.mechanic.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.kiriost.game.mechanic.Grid;
import com.kiriost.game.mechanic.pathfinding.Path;

/**
 * Created by kiriost on 14/04/16.
 */
public class Movement {
    private Array<Step> steps;

    public Movement() {
        steps = new Array<Step>();
    }

    public void setPath(Path path) {
        if(steps.size > 1) {
            steps.removeRange(1, steps.size - 1);
        }


        Path.Step firstStep = path.getStep(0);
        float lastX = Grid.pixels(firstStep.getX());
        float lastY = Grid.pixels(firstStep.getY());

        for (int i = 0; i < path.getLength(); i++) {
            Path.Step step = path.getStep(i);
            System.out.println(step.getX() + " -- " + step.getY());
            float x = Grid.pixels(step.getX());
            float y = Grid.pixels(step.getY());

            steps.add(new Step(lastX, lastY, x, y));

            lastX = x;
            lastY = y;
        }
    }

    public void concatPath(Path path) {
    }

    public Vector2 nextStep() {
        Step currentStep = steps.first();
        Vector2 step = currentStep.nextRelativeStep();
        if(!currentStep.hasNextRelativeStep()) {
            steps.removeIndex(0);
        }

        return step;
    }

    public Vector2 nextAbsoluteStep() {
        return steps.first().absoluteStep;
    }

    public boolean hasNextStep() {
        return steps.size > 0;
    }

    private class Step {
        private Vector2 absoluteStep;
        private Vector2[] relativeSteps;
        private int counter;

        public Step(float srcX, float srcY, float dstX, float dstY) {
            absoluteStep = new Vector2(dstX, dstY);

            relativeSteps = new Vector2[10];
            for (int i = 0; i < 10; i++) {
                relativeSteps[i] = new Vector2((dstX - srcX) * 0.1f, (dstY - srcY) * 0.1f);
            }

            counter = 0;
        }

        public boolean hasNextRelativeStep() {
            return counter < 10;
        }

        public Vector2 nextRelativeStep() {
            return relativeSteps[counter++];
        }
    }
}
