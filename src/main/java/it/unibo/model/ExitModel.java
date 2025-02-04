package it.unibo.model;

import it.unibo.model.interfaces.ExitModelInterface;

public class ExitModel implements ExitModelInterface{
    private boolean exitClicked;

    public ExitModel() {
        this.exitClicked = false;
    }

    @Override
    public boolean isExitClicked() {
        return exitClicked;
    }

    @Override
    public void setExitClicked(boolean exitClicked) {
        this.exitClicked = exitClicked;
    }
}
