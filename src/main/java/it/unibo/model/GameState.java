//FEDE
//tiene traccia dello stato generale del gioco(punteggio, livello corrente, ecc..)

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

    public GameState() {
        this.score = 0;
        this.level = 1;
        this.isPaused = false;
        this.isGameOver = false;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        this.level++;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void togglePause() {
        this.isPaused = !this.isPaused;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
