package it.unibo.model;

import java.util.Optional;

public class StatusModel {
    private boolean gameEnded;
    private boolean gamePaused;
    private Optional<Integer> endStars; 

    public StatusModel() {
        this.gameEnded = false;
        this.gamePaused = true;
        this.endStars = Optional.empty(); 
    }

    public void setGameEnded() {
        this.gameEnded = true;
    }

    public void togglePaused() {
        this.gamePaused = false;
    }

    public void setStars(int stars){
        this.endStars = Optional.of(stars);        
    }
}