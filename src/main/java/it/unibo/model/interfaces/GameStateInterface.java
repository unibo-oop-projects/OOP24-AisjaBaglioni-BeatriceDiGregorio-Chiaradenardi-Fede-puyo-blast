package it.unibo.model.interfaces;

public interface GameStateInterface {
    boolean isGameOver(final int currentScore, final boolean gridIsFull);
    boolean getIsGameOver();
}
