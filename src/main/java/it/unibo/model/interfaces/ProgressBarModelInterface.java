package it.unibo.model.interfaces;

public interface ProgressBarModelInterface {

    int getChargeLevel();

    void resetCharge();

    int getProgress();

    void setProgress(int chargeLevel);

    void incrementProgress(int increment);
}
