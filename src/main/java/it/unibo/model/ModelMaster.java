package it.unibo.model;


import it.unibo.model.interfaces.ModelMasterInterface;

public class ModelMaster implements ModelMasterInterface{

    private final BulletModel bulletModel;
    private final CannonModel cannonModel;
    private final ExitModel exitModel;
    private final Grid grid;
    private final KeyboardModel keyboardModel;
    private final PauseModel pauseModel;
    private final ProgressBarModel progressBarModel;
    private final ScoreModel scoreModel;
    private final TryAgainModel tryAgainModel;

    public ModelMaster() {
        this.bulletModel = new BulletModel();
        this.cannonModel = new CannonModel();
        this.exitModel = new ExitModel();
        this.grid = new Grid(8, 8);
        this.keyboardModel = new KeyboardModel();
        this.pauseModel = new PauseModel();
        this.progressBarModel = new ProgressBarModel();
        this.scoreModel = new ScoreModel();
        this.tryAgainModel = new TryAgainModel();
        };

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

    @Override
    public ExitModel getExitModel() {
        return this.exitModel;
    }

    @Override
    public TryAgainModel getTryAgainModel() {
        return this.tryAgainModel;
    }
    
}