package it.unibo.controller.interfaces;

import it.unibo.controller.GameController;
import it.unibo.controller.BulletController;
import it.unibo.controller.CannonController;
import it.unibo.controller.KeyboardController;
import it.unibo.controller.PauseController;
import it.unibo.controller.ProgressBarController;

public interface ControllerMasterInterface {

    GameController getGameController();

    CannonController getCannonController();

    BulletController getBulletController();

    KeyboardController getKeyboardController();

    PauseController getPauseController();

    ProgressBarController getProgressBarController();

}
