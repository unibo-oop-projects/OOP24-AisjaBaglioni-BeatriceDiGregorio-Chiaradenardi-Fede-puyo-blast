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

    // Metodo per aggiornare l'angolo del cannone
    public void updateCannonAngle(final int angle) {
        // logica per aggiornare la rotazione dell'immagine
    }

}
