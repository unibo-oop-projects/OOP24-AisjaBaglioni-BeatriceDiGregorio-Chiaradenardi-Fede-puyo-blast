package it.unibo.controller;

import it.unibo.model.CannonModel;
import it.unibo.view.CannonView;

public class CannonController {
    private final CannonModel cannonModel;
    private final CannonView cannonView;
    private final InputHandler inputHandler;

    public CannonController(CannonView view) {
        this.cannonModel = new CannonModel();
        this.cannonView = view;
        this.cannonView.setCannonPosition(350, 450);
        this.inputHandler = new InputHandler(this, cannonModel);
    }

    public CannonModel getModel() {
        return this.cannonModel;
    }

    public CannonView getView(){
        return this.cannonView;
    }

    public InputHandler getInputHandler() {
        return this.inputHandler;
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
