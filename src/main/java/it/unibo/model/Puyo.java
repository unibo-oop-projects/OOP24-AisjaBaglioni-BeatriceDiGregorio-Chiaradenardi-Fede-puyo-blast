//FEDE

package it.unibo.model;

import it.unibo.model.interfaces.GridInterface;
import it.unibo.model.interfaces.PuyoInterface;
import java.util.concurrent.ThreadLocalRandom;

public class Puyo implements PuyoInterface {
    private long identifier;
    private String color;
    private int x;
    private int y;
    private boolean isFalling;

    // Costruttore
    public Puyo(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.identifier = Math.abs(ThreadLocalRandom.current().nextLong());
        this.isFalling = true;
    }

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
    public boolean isFalling() {
        return isFalling;
    }

    @Override
    public void setFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    @Override
    public void moveDown() {
        if (isFalling) {
            y += 1; // Incrementa la posizione verticale
        }
    }

    @Override
    public boolean checkCollision(GridInterface grid) {
        // Controlla se il Puyo ha raggiunto il fondo o se c'è un altro Puyo sotto
        if (!grid.isValidPosition(x, y + 1) || grid.getPuyo(x, y + 1) != null) {
            isFalling = false; // Ferma il movimento del Puyo
            return true;
        }
        return false;
    }

    @Override
    public void explode() {
        System.out.println("Puyo at (" + x + ", " + y + ") exploded!");
    }

    @Override
    public void updateGrid(GridInterface grid) {
        // Rimuovi il Puyo dalla posizione corrente nella griglia
        grid.removePuyo(x, y);

        // Se il Puyo è in caduta, aggiornalo nella nuova posizione
        if (isFalling && grid.isValidPosition(x, y + 1)) {
            y += 1; // Aggiorna la posizione del Puyo
            grid.addPuyo(this, x, y);
        }
    }
}
