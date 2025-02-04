//FEDE 

package it.unibo.model;

import it.unibo.model.interfaces.PauseModelInterface;

public class PauseModel implements PauseModelInterface {
    private boolean isPaused;


    public PauseModel() {
        this.isPaused = false;
    }


    public void setPause(boolean pause) {
        this.isPaused = pause;
    }


    public void togglePause() {
        this.isPaused = !this.isPaused;
    }


    public boolean getPause() {
        return this.isPaused;
    }
}
