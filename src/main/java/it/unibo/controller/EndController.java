package it.unibo.controller;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.ScoreModel;
import it.unibo.model.StatusModel;

public class EndController implements TickListenerInterface {
    private final Grid grid;
    private final ScoreModel score;
    private final StatusModel gameStatus;
    private static final int[] THRESHOLDS = { 100, 200, 300 };

    public EndController(Grid grid, ScoreModel score, StatusModel gameStatus) {
        this.grid = grid;
        this.score = score;
        this.gameStatus = gameStatus;
    }

    @Override
    public void onTick() {
        if (this.score.getScore() >= THRESHOLDS[2]) {
            this.gameStatus.setStars(3);
        } else if (this.score.getScore() >= THRESHOLDS[1]) {
            this.gameStatus.setStars(2);
        } else if (this.score.getScore() >= THRESHOLDS[0]) {
            this.gameStatus.setStars(1);
        }

        if (this.grid.isGridFull()) {
            this.gameStatus.setGameEnded();
        }
    }
}