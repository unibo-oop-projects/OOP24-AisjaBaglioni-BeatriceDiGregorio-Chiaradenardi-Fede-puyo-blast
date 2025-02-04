package it.unibo.model;

import it.unibo.model.interfaces.TryAgainModelInterface;

public class TryAgainModel implements TryAgainModelInterface{
    private boolean isEnabled;

    public TryAgainModel() {
        this.isEnabled = true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}
