package it.unibo.model;

import it.unibo.model.interfaces.ProgressBarModelInterface;

public class ProgressBarModel implements ProgressBarModelInterface {
    private int chargeLevel;
    private final int maxCharge;

    public ProgressBarModel() {
        this.chargeLevel = 0;
        this.maxCharge = 100;
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
}
