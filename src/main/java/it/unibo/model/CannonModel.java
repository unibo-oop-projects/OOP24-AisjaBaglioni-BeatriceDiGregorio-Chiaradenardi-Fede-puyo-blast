package it.unibo.model;

import it.unibo.model.interfaces.CannonInterface;

public class CannonModel implements CannonInterface {
    private int chargeLevel;
    private final int maxCharge;
    private int x;
    private int y;

    public CannonModel() {
        this.chargeLevel = 0;
        this.maxCharge = 100;
        this.x = 0;
        this.y = 0;
    }

    @Override
    final public void increaseCharge() {
        if (this.chargeLevel < this.maxCharge) {
            this.chargeLevel++;
        }
    }

    @Override
    final public void decreaseCharge() {
        if (this.chargeLevel > 0) {
            this.chargeLevel--;
        }
    }

    @Override
    final public int getChargeLevel() {
        return this.chargeLevel;
    }

    @Override
    final public void resetCharge() {
        this.chargeLevel = 0;
    }

    @Override
    final public void moveLeft() {
        this.x--;
    }

    @Override
    final public void moveRight() {
        this.x++;
    }

    @Override
    final public void moveUp() {
        // limite della y
        this.y--;
    }

    @Override
    final public void moveDown() {
        // limite della y < 0
        this.y++;
    }

    @Override
    final public int getX() {
        return this.x;
    }

    @Override
    final public int getY() {
        return this.y;
    }

}
