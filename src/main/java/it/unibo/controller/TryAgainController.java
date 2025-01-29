package it.unibo.controller;

import it.unibo.model.TryAgainModel;

public class TryAgainController {
    private LevelManager levelManager;
    private ScreenManager screenManager;
    private TryAgainModel model;  // Riferimento al modello

    public TryAgainController(LevelManager levelManager, ScreenManager screenManager, TryAgainModel model) {
        this.levelManager = levelManager;
        this.screenManager = screenManager;
        this.model = model;  // Assegna il modello
    }

    public void handleClick() {
        System.out.println("Riavvio del gioco...");

        // Ottieni il livello corrente dal LevelManager
        int currentLevel = levelManager.getCurrentLevel();  // Aggiungi questo per ottenere il livello corrente

        // Reset del livello e del gioco
        if (levelManager != null) {
            levelManager.resetLevel(currentLevel);  // Passa il livello corrente al resetLevel
        }
        
        if (screenManager != null) {
            screenManager.resetGame();  // Reset completo del gioco
        }

        // Modifica lo stato del modello per indicare che l'azione è stata eseguita
        model.setEnabled(false);  // Supponiamo che il pulsante venga disabilitato dopo un click
        System.out.println("Pulsante disabilitato: " + model.isEnabled());
    }
}
