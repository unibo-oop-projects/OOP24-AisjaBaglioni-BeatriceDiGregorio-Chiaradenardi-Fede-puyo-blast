package it.unibo.model;

import it.unibo.model.interfaces.ProgressBarModelInterface;

public class ProgressBarModel implements ProgressBarModelInterface {
    private double chargeLevel;
    private static final double STEP = 0.002;

    public ProgressBarModel() {
        this.chargeLevel = 0;
    }

    @Override
    final public double getChargeLevel() {
        return this.chargeLevel;
    }

    @Override
    final public void resetCharge() {
        this.chargeLevel = 0;
    }

    @Override
    final public void incrementProgress() {
        this.chargeLevel = Math.min(1, this.chargeLevel + STEP);
    }
}
