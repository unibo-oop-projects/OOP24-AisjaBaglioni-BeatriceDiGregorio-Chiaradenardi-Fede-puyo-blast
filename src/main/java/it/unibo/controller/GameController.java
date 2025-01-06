//FEDE 

//pausa: bloccare il movimento dei puyo, sospendere il movimento dei puyo, sospendere il timer o il ciclo di aggiornamento del gioco


//Coordina le azioni del gioco, come far cadere i Puyo, gestire esplosioni e aggiornare il punteggio.


//bloccare il movimento dei pujo (fede),  Integrare la pausa nel ciclo di aggiornamento del gioco. (fede)

package it.unibo.controller;


import it.unibo.model.GameState;
import it.unibo.model.ScoreModel;
import it.unibo.controller.interfaces.GameControllerInterface;

public class GameController implements GameControllerInterface {
    private GameState gameState;
    private final ScoreModel score;

    public GameController(GameState gameState) {
        this.gameState = gameState;
        this.score = score;
    }

    public void togglePause() {
        gameState.togglePause();
        if (gameState.isPaused()) {
            System.out.println("Game paused");
            // Blocca il ciclo di gioco
        } else {
            System.out.println("Game resumed");
            // Riprendi il ciclo di gioco
        }
    }

    @Override
    final public void updateGame(final boolean gridIsFull) {
        if (gameState.isGameOver(score.getScore(), gridIsFull)) {
            System.out.println("Game Over!");
            /* Mancano le azioni di fine partita */

        }
    }
}

