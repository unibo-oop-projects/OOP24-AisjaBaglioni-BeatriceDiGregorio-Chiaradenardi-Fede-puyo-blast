package it.unibo.controller;

import it.unibo.controller.interfaces.CannonSightControllerInterface;
import it.unibo.model.CannonSightModel;
import it.unibo.view.CannonSightView;

public class CannonSightController implements CannonSightControllerInterface {
    private final CannonSightModel cannonSightModel;
    private final CannonSightView cannonSightView;

    public CannonSightController(final CannonSightView view) {
        this.cannonSightModel = new CannonSightModel();
        this.cannonSightView = view;
        this.cannonSightView.setCannonSightPosition(300, 400);

    }

    @Override
    final public CannonSightModel getModel() {
        return this.cannonSightModel;
    }

    @Override
    final public CannonSightView getView() {
        return this.cannonSightView;
    }

    // Metodo per aggiornare la posizione del mirino
    @Override
    final public void updateCannonSightPosition() {
        final int newX = cannonSightModel.getX();
        final int newY = cannonSightModel.getY();
        cannonSightView.setCannonSightPosition(newX, newY);
    }

}
