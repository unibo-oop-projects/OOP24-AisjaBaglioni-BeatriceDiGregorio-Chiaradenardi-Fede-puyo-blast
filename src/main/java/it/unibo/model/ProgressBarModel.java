package it.unibo.model;

import it.unibo.model.interfaces.ProgressBarModelInterface;

public class ProgressBarModel implements ProgressBarModelInterface {
    private int chargeLevel;

    public ProgressBarModel() {
        this.chargeLevel = 0;
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
    final public int getProgress() {
        return this.chargeLevel;
    }

    @Override
    final public void setProgress(final int chargeLevel) {
        if (chargeLevel < 0) {
            this.chargeLevel = 0;
        } else if (chargeLevel > 100) {
            this.chargeLevel = 100;
        } else {
            this.chargeLevel = chargeLevel;
        }
    }

    @Override
    final public void incrementProgress(final int increment) {
        setProgress(this.chargeLevel + increment);
    }
}
