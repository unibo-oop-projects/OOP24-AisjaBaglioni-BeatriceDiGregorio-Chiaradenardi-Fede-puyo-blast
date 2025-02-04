//FEDE 

package it.unibo.model;

import it.unibo.model.interfaces.PauseModelInterface;

public class PauseModel implements PauseModelInterface {
    private boolean isPaused;


    public PauseModel() {
        this.isPaused = false;
    }


    @Override
    public void setPause(boolean pause) {
        this.isPaused = pause;
    }


    @Override
    public void togglePause() {
        this.isPaused = !this.isPaused;
    }


    @Override
    public boolean getPause() {
        return this.isPaused;
    }
}
