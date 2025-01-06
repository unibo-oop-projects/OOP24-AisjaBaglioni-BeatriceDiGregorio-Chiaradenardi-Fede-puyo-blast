package it.unibo.controller;

import it.unibo.model.CannonModel;
import it.unibo.view.CannonView;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;


public class CannonController {
    private CannonModel model;
    private CannonView view;

    public CannonController(CannonModel model, CannonView view) {
        this.model = model;
        this.view = view;

        // Aggiungi un listener per la tastiera

        
    }

    // Aggiungi un listener per la tastiera
    private void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.SPACE) {
            // Logica per sparare
            System.out.println("Sparato con forza: " + model.getChargeLevel());
            model.resetCharge(); 
        } else if (code == KeyCode.UP) {
            model.increaseCharge(); // Aumenta la carica
        } else if (code == KeyCode.DOWN) {
            model.decreaseCharge(); // Diminuisci la carica
        } else if (code == KeyCode.W) {
            model.moveUp(); // Sposta il cannone verso l'alto
        } else if (code == KeyCode.S) {
            model.moveDown(); // Sposta il cannone verso il basso
        } else if (code == KeyCode.A) {
            model.moveLeft(); // Sposta il cannone verso sinistra
        } else if (code == KeyCode.D) {
            model.moveRight(); // Sposta il cannone verso destra
        }
    
        view.update(); // Aggiorna la vista per riflettere i cambiamenti
    }
}
