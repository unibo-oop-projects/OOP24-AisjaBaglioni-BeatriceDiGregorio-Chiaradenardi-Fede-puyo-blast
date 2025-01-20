package it.unibo.controller.interfaces;

import it.unibo.model.CannonSightModel;
import it.unibo.view.CannonSightView;

public interface CannonSightControllerInterface {
    CannonSightModel getModel();

    CannonSightView getView();

    void updateCannonSightPosition();
}
