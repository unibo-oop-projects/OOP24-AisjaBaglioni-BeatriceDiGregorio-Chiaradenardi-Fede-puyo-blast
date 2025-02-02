package it.unibo.model.interfaces;

import it.unibo.model.BulletModel;
import it.unibo.model.CannonModel;
import it.unibo.model.ExitModel;
import it.unibo.model.Grid;
import it.unibo.model.KeyboardModel;
import it.unibo.model.PauseModel;
import it.unibo.model.ProgressBarModel;
import it.unibo.model.ScoreModel;
import it.unibo.model.TryAgainModel;

public interface ModelMasterInterface {
    Grid getGrid();

    CannonModel getCannonModel();

    ProgressBarModel getProgressBarModel();

    BulletModel getBulletModel();

    KeyboardModel getKeyboardModel();

    PauseModel getPauseModel();

    ScoreModel getScoreModel();

    ExitModel getExitModel();

    TryAgainModel getTryAgainModel();
}