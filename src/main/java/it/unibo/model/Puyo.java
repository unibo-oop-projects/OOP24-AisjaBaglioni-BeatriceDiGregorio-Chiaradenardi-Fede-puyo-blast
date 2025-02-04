//FEDE

package it.unibo.model;

import it.unibo.model.interfaces.PuyoInterface;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Puyo implements PuyoInterface {
    private long identifier;
    private String color;
    private int x;
    private int y;

    Optional<Integer> deathClock;
    Optional<Integer> freezeClock;

    // Costruttore
    public Puyo(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.identifier = Math.abs(ThreadLocalRandom.current().nextLong());

        deathClock = Optional.empty();
        freezeClock = Optional.empty();
    }

    @Override
    public Optional<Integer> getDeathClock() {
        return deathClock;
    }

    @Override
    public void setDeathClock(Optional<Integer> deathClock) {
        this.deathClock = deathClock;
    }

    @Override
    public void setFreezeClock(Optional<Integer> freezeClock) {
        this.freezeClock = freezeClock;
    }

    @Override
    public Optional<Integer> getFreezeClock() {
        return this.freezeClock;
    }

    @Override
    public long getIdentifier() {
        return identifier;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void moveDown() {
        y += 1;
    }
}
