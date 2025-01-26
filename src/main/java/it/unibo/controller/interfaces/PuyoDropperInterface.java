//chiara
package it.unibo.controller.interfaces;

import it.unibo.model.Puyo;

public interface PuyoDropperInterface{
    /**
     * Metodo per spawnare un nuovo Puyo e farlo cadere
     */
    void spawnAndDropPuyo();

    /**
     * Trova la prima posizione disponibile per un Puyo in una colonna
     * 
     * @param x La colonna in cui cercare
     * @return La posizione Y disponibile, o -1 se non c'è spazio
     */
    int findAvailableYPosition(int x);

    /**
     * Logica di caduta di un Puyo
     * 
     * @param puyo Il Puyo che deve cadere
     */
    void dropPuyo(Puyo puyo);

    void onTick();
}

