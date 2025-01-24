//AISJA

package it.unibo.model;

public class PauseModel {
    private boolean isPaused;

    public PauseModel(){
        this.isPaused = false;
    }

    public void changePause(){
        if (this.isPaused == false) {
            this.isPaused = true;
        } else {
            this.isPaused = false;
        }
    }

    public boolean getPause(){
        return this.isPaused;
    }
}
