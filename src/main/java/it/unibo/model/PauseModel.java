//FEDE 

package it.unibo.model;

public class PauseModel {
    private boolean isPaused;

    public PauseModel(){
        this.isPaused = false;
    }

    public void setPause(boolean pause){
        this.isPaused = pause;
    }

    public boolean getPause(){
        return this.isPaused;
    }
}
