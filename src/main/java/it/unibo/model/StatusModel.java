package it.unibo.model;

import java.util.Optional;

public class StatusModel {
    private boolean gameEnded;
    private boolean gamePaused;
    private Optional<Integer> endStars; 

    public StatusModel() {
        this.gameEnded = false;
        this.gamePaused = false;
        this.endStars = Optional.empty(); 
    }

    public void setGameEnded() {
        this.gameEnded = true;
    }

    public void togglePaused() {
        this.gamePaused = !this.gamePaused;
    }

    public void setStars(int stars){
        this.endStars = Optional.of(stars);
    }

    public boolean getGameEnded(){
        return this.gameEnded;
    }

    public boolean getGamePaused(){
        return this.gamePaused;
    }

    public Optional<Integer> getEndStars(){
        return this.endStars;
    }
}