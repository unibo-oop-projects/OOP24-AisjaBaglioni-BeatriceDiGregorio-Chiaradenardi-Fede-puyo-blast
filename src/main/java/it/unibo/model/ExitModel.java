package it.unibo.model;

public class ExitModel {
    private boolean exitClicked;

    public ExitModel() {
        this.exitClicked = false;
    }

    public boolean isExitClicked() {
        return exitClicked;
    }

    public void setExitClicked(boolean exitClicked) {
        this.exitClicked = exitClicked;
    }
}
