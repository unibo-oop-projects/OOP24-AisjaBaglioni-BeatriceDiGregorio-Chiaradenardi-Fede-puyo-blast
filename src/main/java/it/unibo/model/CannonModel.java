package it.unibo.model;

import it.unibo.model.interfaces.CannonModelInterface;

public class CannonModel implements CannonModelInterface {
    private int x;
    private int y;
    private int angle;

    public CannonModel() {
        this.x = 310;
        this.y = 200;
        this.angle = 0;
    }

    @Override
    final public void moveLeft() {
        this.x = this.x - 5;
    }

    @Override
    final public void moveRight() {
        this.x = this.x + 5;
    }

    @Override
    final public int getX() {
        return this.x;
    }

    @Override
    final public void aimUp() {
        if (this.angle < 90) {
            this.angle++;
        }
    }

    @Override
    final public void aimDown() {
        if (this.angle > -90) {
            this.angle--;
        }
    }

    @Override
    final public int getAngle() {
        return this.angle;
    }

}
