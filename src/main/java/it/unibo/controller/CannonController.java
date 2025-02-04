package it.unibo.controller;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.CannonModel;
import it.unibo.model.KeyboardModel;

import java.awt.event.KeyEvent;

public class CannonController implements TickListenerInterface{
    private final CannonModel cannonModel;
    private final KeyboardModel keyboardModel;

    public CannonController(CannonModel cannonModel, KeyboardModel keyboardModel, ProgressBarController progressBar) {
        this.cannonModel = cannonModel;
        this.keyboardModel = keyboardModel;
    }

    @Override
    public void onTick() {
        if (this.keyboardModel.isKeyPressed(KeyEvent.VK_RIGHT)) {
            cannonModel.moveRight();
        } else if (this.keyboardModel.isKeyPressed(KeyEvent.VK_LEFT)) {
            cannonModel.moveLeft();
        }
        if (this.keyboardModel.isKeyPressed(KeyEvent.VK_UP)) {
            cannonModel.aimUp();
        } else if (this.keyboardModel.isKeyPressed(KeyEvent.VK_DOWN)) {
            cannonModel.aimDown();
        }
    }
}
