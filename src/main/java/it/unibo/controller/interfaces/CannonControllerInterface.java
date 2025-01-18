package it.unibo.controller.interfaces;

import it.unibo.model.CannonModel;
import it.unibo.view.CannonView;

public interface CannonControllerInterface {
    CannonModel getModel();

    CannonView getView();

    void updateCannonPosition();

    void updateCannonAngle();
}
