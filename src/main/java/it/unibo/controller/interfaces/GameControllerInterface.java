package it.unibo.controller.interfaces;

public interface GameControllerInterface {

    /**
     * Alterna lo stato di pausa del gioco.
     * Se il gioco è in pausa, lo riprende. Se è in corso, lo mette in pausa.
     */
    void togglePause();

    void updateGame(boolean gridIsFull);
}
