package it.unibo.model;

import it.unibo.model.interfaces.GameStateInterface;

public class GameState implements GameStateInterface {
    private boolean isGameOver = false;
    private final int minScoreForOneStar;

    public GameState(final int minScoreForOneStar) {
        this.minScoreForOneStar = minScoreForOneStar;
    }

    @Override
    final public boolean isGameOver(final int currentScore, final boolean gridIsFull) {
        if (gridIsFull && currentScore < minScoreForOneStar) {
            isGameOver = true;
        }
        return isGameOver;
    }

    @Override
    final public boolean getIsGameOver() {
        return isGameOver;
    }

}
