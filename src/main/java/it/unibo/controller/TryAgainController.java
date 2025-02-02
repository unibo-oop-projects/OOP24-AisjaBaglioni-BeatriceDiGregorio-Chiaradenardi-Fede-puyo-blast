package it.unibo.controller;

import it.unibo.model.TryAgainModel;

public class TryAgainController {
    private LevelManager levelManager;
    private ScreenManager screenManager;
    private TryAgainModel model; // Riferimento al modello

    public TryAgainController(LevelManager levelManager, ScreenManager screenManager, TryAgainModel model) {
        this.levelManager = levelManager;
        this.screenManager = screenManager;
        this.model = model; // Assegna il modello
    }

    public void handleClick() {
        System.out.println("Riavvio del gioco...");
        int currentLevel = levelManager.getCurrentLevel();

        // Reset del livello e del gioco
        if (levelManager != null) {
            levelManager.resetLevel(currentLevel);
        }

        if (screenManager != null) {
            screenManager.resetGame(); // Reset completo del gioco
        }

        // Modifica lo stato del modello per indicare che l'azione è stata eseguita
        model.setEnabled(false);
        System.out.println("Pulsante disabilitato: " + model.isEnabled());
    }
}
