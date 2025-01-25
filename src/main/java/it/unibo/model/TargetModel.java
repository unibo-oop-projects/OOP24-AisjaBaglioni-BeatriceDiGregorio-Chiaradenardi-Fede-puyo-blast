package it.unibo.model;

import it.unibo.model.interfaces.TargetModelInterface;

public class TargetModel implements TargetModelInterface {
    private int x;
    private int y;

    public TargetModel() {
        this.x = 300;
        this.y = 400;
    }

    @Override
    final public void moveLeft() {
        this.x = this.x - 3;
    }

    @Override
    final public void moveRight() {
        this.x = this.x + 3;
    }

    @Override
    final public int getX() {
        return this.x;
    }

    @Override
    final public void moveUp() {
        this.y = this.y - 3;
    }

    @Override
    final public void moveDown() {
        this.y = this.y + 3;
    }

    @Override
    final public int getY() {
        return this.y;
    }
}
