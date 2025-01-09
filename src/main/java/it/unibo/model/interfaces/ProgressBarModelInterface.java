package it.unibo.model.interfaces;

public interface ProgressBarModelInterface {
    void increaseCharge();

    void decreaseCharge();

    int getChargeLevel();

    void resetCharge();
}
