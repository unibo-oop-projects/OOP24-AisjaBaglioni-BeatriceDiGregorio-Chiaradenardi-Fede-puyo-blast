package it.unibo.model;

public class TryAgainModel {
    private boolean isEnabled;

    public TryAgainModel() {
        this.isEnabled = true;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}
