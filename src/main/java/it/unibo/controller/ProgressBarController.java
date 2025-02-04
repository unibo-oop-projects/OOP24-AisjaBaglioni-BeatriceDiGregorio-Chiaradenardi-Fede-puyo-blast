package it.unibo.controller;

import it.unibo.controller.interfaces.ProgressBarControllerInterface;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.ProgressBarModel;

public class ProgressBarController implements TickListenerInterface, ProgressBarControllerInterface {
    private final ProgressBarModel progressBarModel;

    public ProgressBarController(ProgressBarModel progressBarModel) {
        this.progressBarModel = progressBarModel;
    }

    @Override
    public void onTick() {
        this.progressBarModel.incrementProgress();
    }

    @Override
    public boolean resetBar() {
        if (this.progressBarModel.getChargeLevel() == 1) {
            this.progressBarModel.resetCharge();
            return true;
        }
        return false;
    }

    @Override
    public void reset() {
        this.progressBarModel.resetCharge(); // Reimposta la barra al valore iniziale
    }
}
