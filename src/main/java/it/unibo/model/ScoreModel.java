package it.unibo.model;

import it.unibo.model.interfaces.ScoreModelInterface;

public class ScoreModel implements ScoreModelInterface {
    private int score;

    public ScoreModel() {
        this.score = 0;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
}