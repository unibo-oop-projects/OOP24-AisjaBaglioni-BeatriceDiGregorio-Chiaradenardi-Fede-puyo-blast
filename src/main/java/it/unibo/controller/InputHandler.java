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
                if (model.getX() - 5 >= 0) {
                    model.moveLeft();
                    controller.updateCannonPosition();
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (model.getX() + 5 <= 700) {
                    model.moveRight();
                    controller.updateCannonPosition();
                }
            }
            case KeyEvent.VK_SPACE -> {
                progressBarController.resetProgressBar();
                progressBarController.startProgress();
                System.out.println("Cannon fired!");
            }
            case KeyEvent.VK_UP -> {
                model.aimUp();
                controller.updateCannonAngle();
            }
            case KeyEvent.VK_DOWN -> {
                model.aimDown();
                controller.updateCannonAngle();
            }
            // movimenti mirino del cannone
            case KeyEvent.VK_A -> {
                if (sightModel.getX() - 3 >= 50) {
                    sightModel.moveLeft();
                    sightController.updateCannonSightPosition();
                }
            }
            case KeyEvent.VK_D -> {
                if (sightModel.getX() + 3 <= 550) {
                    sightModel.moveRight();
                    sightController.updateCannonSightPosition();
                }
            }
            case KeyEvent.VK_W -> {
                if (sightModel.getY() - 3 >= 25) {
                    sightModel.moveUp();
                    sightController.updateCannonSightPosition();
                }
            }
            case KeyEvent.VK_S -> {
                if (sightModel.getY() + 3 <= 400) {
                    sightModel.moveDown();
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