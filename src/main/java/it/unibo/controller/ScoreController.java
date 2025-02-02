package it.unibo.controller;

import it.unibo.model.ScoreModel;

public class ScoreController {
    private final ScoreModel scoreModel;

    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;
    }

    public void addPoints(int power) {
        int newPoints = (int) Math.pow(power, 2);
        int oldScore = this.scoreModel.getScore();
        this.scoreModel.setScore(oldScore + newPoints);
    }
}