package it.unibo.controller.interfaces;

import it.unibo.model.CannonModel;

public interface CannonControllerInterface {
    CannonModel getModel();

    void onTick();
}
