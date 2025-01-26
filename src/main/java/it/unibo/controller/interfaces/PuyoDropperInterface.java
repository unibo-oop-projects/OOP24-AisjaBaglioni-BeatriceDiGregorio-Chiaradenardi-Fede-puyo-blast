//chiara
package it.unibo.controller.interfaces;

public interface PuyoDropperInterface {
    /**
     * Metodo per spawnare un nuovo Puyo e farlo cadere
     */
    void spawnAndDropPuyo();

    /**
     * Logica di caduta dei Puyo
     */
    void dropPuyo();
}