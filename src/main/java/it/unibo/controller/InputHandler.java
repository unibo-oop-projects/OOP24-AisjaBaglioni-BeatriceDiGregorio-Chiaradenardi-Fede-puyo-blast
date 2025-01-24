package it.unibo.controller;
//Traduce gli input del giocatore (tasti, mouse) in azioni. (NO FEDE)

import it.unibo.controller.interfaces.InputHandlerInterface;
import it.unibo.model.CannonModel;
import it.unibo.model.CannonSightModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements InputHandlerInterface, KeyListener {
    private final CannonModel model;
    private final CannonController controller;
    private final ProgressBarController progressBarController;
    private final CannonSightModel sightModel;
    private final CannonSightController sightController;

    public InputHandler(CannonController controller, CannonModel model, ProgressBarController progressBarController,
            CannonSightController sightController, CannonSightModel sightModel) {
        this.controller = controller;
        this.model = model;
        this.progressBarController = progressBarController;
        this.sightModel = sightModel;
        this.sightController = sightController;

    }

    // Metodo per gestire l'input da tastiera
    @Override
    final public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // movimenti del cannone
            case KeyEvent.VK_LEFT -> {
                if (model.getX() - 3 >= 10) {
                    model.moveLeft();
                    sightModel.moveLeft();
                    controller.updateCannonPosition();
                    sightController.updateCannonSightPosition();
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (model.getX() + 3 <= 600) {
                    model.moveRight();
                    sightModel.moveRight();
                    controller.updateCannonPosition();
                    sightController.updateCannonSightPosition();
                }
            }
            case KeyEvent.VK_SPACE -> {
                if (progressBarController.getProgress() == 100) {
                    progressBarController.resetProgressBar();
                    progressBarController.startProgress();
                }
                System.out.println("Cannon fired!");
            }
            case KeyEvent.VK_UP -> {
                if (sightModel.getY() - 3 >= 25) {
                    model.aimUp();
                    sightModel.moveUp();
                    controller.updateCannonAngle();
                    sightController.updateCannonSightPosition();
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (sightModel.getY() + 3 <= 400) {
                    model.aimDown();
                    sightModel.moveDown();
                    controller.updateCannonAngle();
                    sightController.updateCannonSightPosition();
                }
            }
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilizzato in questo esempio, ma potrebbe essere necessario in futuro
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilizzato in questo esempio
    }

}