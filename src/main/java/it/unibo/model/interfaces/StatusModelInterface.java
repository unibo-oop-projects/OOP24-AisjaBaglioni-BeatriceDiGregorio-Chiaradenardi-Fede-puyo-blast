package it.unibo.model.interfaces;

import java.util.Optional;

public interface StatusModelInterface {
    void setGameEnded();

    void setStars(int stars);

    boolean isGameEnded();

    Optional<Integer> getEndStars();
}
