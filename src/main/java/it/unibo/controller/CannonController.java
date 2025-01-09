package it.unibo.controller;

import it.unibo.model.CannonModel;
import it.unibo.view.CannonView;

public class CannonController {
    private final CannonModel cannonModel;
    private final CannonView cannonView;
    private final InputHandler inputHandler;

    public CannonController(CannonModel model, CannonView view, int windowWidth) {
        this.cannonModel = model;
        this.cannonView = view;
        this.inputHandler = new InputHandler(model, view, windowWidth);
    }

    public CannonModel getModel() {
        return cannonModel;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

}
