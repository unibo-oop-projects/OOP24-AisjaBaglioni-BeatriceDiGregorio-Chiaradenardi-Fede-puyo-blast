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

    @Override
    public void setGameEnded() {
        this.gameEnded = true;
    }

    @Override
    public void setStars(int stars) {
        this.endStars = Optional.of(stars);
    }

    @Override
    public boolean isGameEnded() {
        return this.gameEnded;
    }

    @Override
    public Optional<Integer> getEndStars() {
        return this.endStars;
    }
}