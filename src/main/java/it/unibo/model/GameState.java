//FEDE
//tiene traccia dello stato generale del gioco(punteggio, livello corrente, ecc..)
//Aggiornare questa classe ogni volta che il punteggio cambia o si verifica un evento importante (es. fine del livello, pausa).

// Questa classe terrà traccia di:
/*
Il punteggio corrente.
Le vite o il numero di stelle raggiunte.
Lo stato del gioco (es. in pausa, in corso, vinto, perso). */

package it.unibo.model;

import it.unibo.model.interfaces.GameStateInterface;

public class GameState implements GameStateInterface {
    private int score;
    private int level;
    private boolean isPaused;
    private boolean isGameOver;

    // soglie di punteggio per passare al livello successivo
    private final int[] levelThresholds = { 1000, 2000, 3000, 4000, 5000 }; // Esempio

    public GameState() {
        this.score = 0;
        this.level = 1;
        this.isPaused = false;
        this.isGameOver = false;
    }

    // getter per il punteggio
    public int getScore() {
        return score;
    }

    // aggiunge punti al punteggio
    public void addScore(int points) {
        this.score += points;
    }

    // getter livello
    public int getLevel() {
        return level;
    }

    // incrementa il livello
    public void nextLevel() {
        this.level++;
    }

    // controlla se il gioco è in pausa
    public boolean isPaused() {
        return isPaused;
    }

    // metodo per mettere in pausa il gioco
    public void togglePause() {
        this.isPaused = !this.isPaused;
    }

    // controlla se il gioco è finito
    public boolean isGameOver() {
        return isGameOver;
    }

    // metodo per impostare il gioco come finito
    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    // resetta lo stato del gioco (restart/nuova partita)
    public void resetGame() {
        this.score = 0;
        this.level = 1;
        this.isPaused = false;
        this.isGameOver = false;
    }
}
