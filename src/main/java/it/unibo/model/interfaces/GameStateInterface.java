//FEDE

package it.unibo.model.interfaces;

public interface GameStateInterface {

    /**
     * Ottiene il punteggio corrente.
     *
     * @return il punteggio corrente
     */
    int getScore();

    /**
     * Aggiunge punti al punteggio corrente.
     *
     * @param points il numero di punti da aggiungere
     */
    void addScore(int points);

    /**
     * Ottiene il livello corrente.
     *
     * @return il livello corrente
     */
    int getLevel();

    /**
     * Passa al livello successivo.
     */
    void nextLevel();

    /**
     * Verifica se il gioco è in pausa.
     *
     * @return true se il gioco è in pausa, false altrimenti
     */
    boolean isPaused();

    /**
     * Cambia lo stato di pausa del gioco.
     */
    void togglePause();

    /**
     * Verifica se il gioco è terminato.
     *
     * @return true se il gioco è terminato, false altrimenti
     */
    boolean isGameOver();

    /**
     * Imposta lo stato di fine gioco.
     *
     * @param isGameOver true per indicare che il gioco è terminato, false altrimenti
     */
    void setGameOver(boolean isGameOver);
}

