package it.unibo.controller;

import it.unibo.controller.interfaces.ScoreControllerInterface;
import it.unibo.model.ScoreModel;

public class ScoreController implements ScoreControllerInterface {
    private final ScoreModel scoreModel;

    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;
    }

    @Override
    public void addPoints(int power) {
        int newPoints = (int) Math.pow(power, 2);
        int oldScore = this.scoreModel.getScore();
        this.scoreModel.setScore(oldScore + newPoints);
    }
}