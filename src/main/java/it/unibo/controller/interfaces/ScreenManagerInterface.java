//chiara
package it.unibo.controller.interfaces;

public interface ScreenManagerInterface {

    /**
     * Avvia la schermata iniziale del gioco.
     */
    void start();

    /**
     * Passa alla vista del menu principale.
     */
    void switchToMenuView();

    /**
     * Passa alla vista delle regole o dei controlli del gioco.
     */
    void switchToRulesView();

    /**
     * Passa alla vista del gioco e avvia il gameplay.
     */
    void switchToGameView();

    /**
     * Mostra un popup con il livello selezionato.
     *
     * @param level il livello selezionato
     */
    void showLevelPopup(String level);
}
