package it.unibo.controller;

import it.unibo.controller.interfaces.GameControllerInterface;
import it.unibo.model.GameState;
import it.unibo.model.ScoreModel;

public class GameController implements GameControllerInterface {
    private final GameState gameState;
    private final ScoreModel score;

    public GameController(GameState gameState, ScoreModel score) {
        this.gameState = gameState;
        this.score = score;
    }

    @Override
    final public void updateGame(final boolean gridIsFull) {
        if (gameState.isGameOver(score.getScore(), gridIsFull)) {
            System.out.println("Game Over!");
            /* Mancano le azioni di fine partita */

        }
    }

}
