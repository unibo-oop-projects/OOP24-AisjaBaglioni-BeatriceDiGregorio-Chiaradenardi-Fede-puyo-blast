package it.unibo.model;

import java.util.Optional;

import it.unibo.model.interfaces.StatusModelInterface;

public class StatusModel implements StatusModelInterface {
    private boolean gameEnded;

    private Optional<Integer> endStars;

    public StatusModel() {
        this.gameEnded = false;
        this.endStars = Optional.empty();
    }

    public void setGameEnded() {
        this.gameEnded = true;
    }

    public void setStars(int stars) {
        this.endStars = Optional.of(stars);
    }

    public boolean isGameEnded() {
        return this.gameEnded;
    }

    public Optional<Integer> getEndStars() {
        return this.endStars;
    }
}