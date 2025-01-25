package it.unibo.model;

import it.unibo.model.interfaces.CannonModelInterface;

public class CannonModel implements CannonModelInterface {
    private double x;
    private double angle;
    private static final double STEP = 0.005;
    private static final double ANGLE_STEP = 1;

    public CannonModel() {
        this.x = 0.5;
        this.angle = 0;
    }

    @Override
    final public void moveLeft() {
        this.x = Math.max(this.x - STEP, 0);
    }

    @Override
    final public void moveRight() {
        this.x = Math.min(this.x + STEP, 1);
    }

    @Override
    final public double getX() {
        return this.x;
    }

    @Override
    final public void aimUp() {
        this.angle = Math.min(this.angle + ANGLE_STEP, 90);
    }

    @Override
    final public void aimDown() {
        this.angle = Math.max(this.angle - ANGLE_STEP, 0);
    }

    @Override
    final public double getAngle() {
        return this.angle;
    }

}
