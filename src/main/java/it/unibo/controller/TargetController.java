package it.unibo.controller;

import it.unibo.controller.interfaces.TargetControllerInterface;
import it.unibo.model.TargetModel;
import it.unibo.view.TargetView;

public class TargetController implements TargetControllerInterface {
    private final TargetModel cannonSightModel;
    private final TargetView cannonSightView;

    public TargetController(final TargetView view) {
        this.cannonSightModel = new TargetModel();
        this.cannonSightView = view;
        this.cannonSightView.setCannonSightPosition(300, 400);

    }

    @Override
    final public TargetModel getModel() {
        return this.cannonSightModel;
    }

    @Override
    final public TargetView getView() {
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
