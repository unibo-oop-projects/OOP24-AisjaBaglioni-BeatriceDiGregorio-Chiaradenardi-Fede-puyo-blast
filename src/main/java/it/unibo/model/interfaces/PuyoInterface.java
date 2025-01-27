package it.unibo.model.interfaces;

import java.util.Optional;

public interface PuyoInterface {
    String getColor();
    void setColor(String color);
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    void moveDown();
    long getIdentifier();
    Optional<Integer> getDeathClock();
    void setDeathClock(Optional<Integer> deathClock);
    Optional<Integer> getFreezeClock();
    void setFreezeClock(Optional<Integer> freezeClock);
}
