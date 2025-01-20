package it.unibo.controller;

import it.unibo.controller.interfaces.CannonControllerInterface;
import it.unibo.model.CannonModel;
import it.unibo.view.CannonView;

public class CannonController implements CannonControllerInterface {
    private final CannonModel cannonModel;
    private final CannonView cannonView;

    public CannonController(final CannonView view) {
        this.cannonModel = new CannonModel();
        this.cannonView = view;
        this.cannonView.setCannonPosition(305, 545);
    }

    @Override
    final public CannonModel getModel() {
        return this.cannonModel;
    }

    @Override
    final public CannonView getView() {
        return this.cannonView;
    }

    // Metodo per aggiornare la posizione del cannone
    @Override
    final public void updateCannonPosition() {
        final int newX = cannonModel.getX();
        final int newY = cannonModel.getY();
        cannonView.setCannonPosition(newX, newY);

    }

    // Metodo per aggiornare l'angolo del cannone
    @Override
    final public void updateCannonAngle() {
        final int newAngle = cannonModel.getAngle();
        cannonView.setCannonAngle(newAngle);
    }

}
