package it.unibo.model;

import it.unibo.model.interfaces.ScoreModelInterface;

public class ScoreModel implements ScoreModelInterface {
    private int score = 0;

    @Override
    final public int getScore() {
        return this.score;
    }

    @Override
    final public void addPoints(final int points) {
        score += points;
    }
}
