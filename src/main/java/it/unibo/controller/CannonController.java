package it.unibo.controller;

import it.unibo.controller.interfaces.CannonControllerInterface;
import it.unibo.model.CannonModel;
import it.unibo.view.CannonView;

public class CannonController implements CannonControllerInterface{
    private final CannonModel cannonModel;
    private final CannonView cannonView;

    public CannonController(CannonView view) {
        this.cannonModel = new CannonModel();
        this.cannonView = view;
        this.cannonView.setCannonPosition(315, 545);
    }

    public CannonModel getModel() {
        return this.cannonModel;
    }

    public CannonView getView(){
        return this.cannonView;
    }

    // Metodo per aggiornare la posizione del cannone
    public void updateCannonPosition() {
        int newX = cannonModel.getX();
        int newY = cannonModel.getY();

        cannonView.setCannonPosition(newX, newY);

    }

    // Metodo per aggiornare l'angolo del cannone
    public void updateCannonAngle() {
        int newAngle = cannonModel.getAngle();

        cannonView.setCannonAngle(newAngle);
    }

}
