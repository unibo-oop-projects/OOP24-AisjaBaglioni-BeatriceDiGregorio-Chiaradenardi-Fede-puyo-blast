package it.unibo.controller.interfaces;

import it.unibo.model.TargetModel;
import it.unibo.view.TargetView;

public interface TargetControllerInterface {
    TargetModel getModel();

    TargetView getView();

    void updateCannonSightPosition();
}
