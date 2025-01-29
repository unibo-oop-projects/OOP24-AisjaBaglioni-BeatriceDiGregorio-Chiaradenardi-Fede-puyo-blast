package it.unibo.model;

import it.unibo.model.interfaces.ModelMasterInterface;

public class ModelMaster implements ModelMasterInterface{

    private final Grid grid;
    private final CannonModel cannonModel;
    private final ProgressBarModel progressBarModel;
    private final BulletModel bulletModel;
    private final KeyboardModel keyboardModel;
    private final PauseModel pauseModel;
    private final ScoreModel scoreModel;

    public ModelMaster() {
        this.grid = new Grid(8, 8);
        this.cannonModel = new CannonModel();
        this.progressBarModel = new ProgressBarModel();
        this.bulletModel = new BulletModel();
        this.keyboardModel = new KeyboardModel();
        this.pauseModel = new PauseModel();
        this.scoreModel = new ScoreModel();
    }

    @Override
    public Grid getGrid() {
        return grid;    
    }

    @Override
    public CannonModel getCannonModel() {
        return cannonModel;
    }

    @Override
    public ScoreModel getScoreModel() {
        return scoreModel;
    }

    @Override
    public ProgressBarModel getProgressBarModel() {
        return progressBarModel;
    }

    @Override
    public BulletModel getBulletModel() {
        return bulletModel;
    }

    @Override
    public KeyboardModel getKeyboardModel() {
        return keyboardModel;
    }

    @Override
    public PauseModel getPauseModel() {
        return pauseModel;
    }
    
}