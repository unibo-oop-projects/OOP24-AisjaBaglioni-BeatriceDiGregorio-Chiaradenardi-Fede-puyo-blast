package it.unibo.controller;

import it.unibo.controller.interfaces.ControllerMasterInterface;
import it.unibo.model.GameState;
import it.unibo.model.ModelMaster;
import it.unibo.model.ScoreModel;
import it.unibo.model.Scale;

public class ControllerMaster implements ControllerMasterInterface {
    private final GameController gameController;
    private final CannonController cannonController;
    private final BulletController bulletController;
    private final KeyboardController keyboardController;
    private final PauseController pauseController;
    private final ProgressBarController progressBarController;
    private final ScoreController scoreController;

    public ControllerMaster(ModelMaster modelMaster) {
        this.gameController = new GameController(new GameState(), new ScoreModel());
        this.progressBarController = new ProgressBarController(modelMaster.getProgressBarModel());
        this.cannonController = new CannonController(modelMaster.getCannonModel(), modelMaster.getKeyboardModel(),
                this.progressBarController);
        this.scoreController = new ScoreController(modelMaster.getScoreModel());
        this.bulletController = new BulletController(modelMaster.getBulletModel(), modelMaster.getGrid(),
                modelMaster.getKeyboardModel(), this.progressBarController, null, this.scoreController, new Scale());
        this.keyboardController = new KeyboardController(modelMaster.getKeyboardModel());
        this.pauseController = new PauseController(modelMaster.getPauseModel());

    }

    @Override
    public GameController getGameController() {
        return gameController;
    }

    @Override
    public CannonController getCannonController() {
        return cannonController;
    }

    @Override
    public BulletController getBulletController() {
        return bulletController;
    }

    @Override
    public KeyboardController getKeyboardController() {
        return keyboardController;
    }

    @Override
    public PauseController getPauseController() {
        return pauseController;
    }

    @Override
    public ProgressBarController getProgressBarController() {
        return progressBarController;
    }
}