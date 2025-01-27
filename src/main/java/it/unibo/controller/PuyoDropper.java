//chiara
package it.unibo.controller;

import it.unibo.controller.interfaces.PuyoDropperInterface;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.model.interfaces.PuyoInterface;
import java.util.Random;

public class PuyoDropper implements PuyoDropperInterface, TickListenerInterface {
    private final Grid grid;
    private final Random random;
    private final String[] colors = { "red", "blue", "green", "yellow", "purple", "cyan", "pink" };
    private final LevelManager.LevelConfig levelConfig;
    private int ticksPassed;

    public PuyoDropper(Grid grid, LevelManager.LevelConfig levelConfig) {
        this.grid = grid;
        this.random = new Random();
        this.levelConfig = levelConfig;
        this.ticksPassed = 0;
    }

    // metodo per generare un Puyo casuale e farlo cadere
    public void spawnAndDropPuyo() {
        if (grid.isRowFull(0)) {
            return;
        }
        // genera un nuovo Puyo con un colore casuale
        String randomColor = colors[random.nextInt(colors.length)];
        // trova una colonna dove c'è spazio per far cadere un Puyo
        int startX = random.nextInt(grid.getCols()); // posizione casuale X
        while (grid.getPuyo(startX, 0) != null) {
            startX = random.nextInt(grid.getCols());
        }
        int startY = 0; // trova la Y disponibile per il Puyo nella colonna
        Puyo puyo = new Puyo(randomColor, startX, startY);
        grid.addPuyo(puyo, startX, startY); // aggiunge il Puyo alla griglia
    }

    public void fillGridRandomly(int puyoCount) {
        for (int i = 0; i < puyoCount; i++) {
            spawnAndDropPuyo(); // Genera e fa cadere un nuovo Puyo
        }
    }

    @Override
    public void onTick() {
        ticksPassed++;
        if (ticksPassed % 5 == 0) {
            dropPuyo();
        }
        if (ticksPassed % levelConfig.getDelay() == 0) {
            fillGridRandomly(levelConfig.getPuyoCount());
            ticksPassed = 0;
        }
    }

    // logica di caduta dei Puyo
    @Override
    public void dropPuyo() {
        for (int y = grid.getRows() - 2; y >= 0; y--) {
            for (int x = 0; x < grid.getCols(); x++) {
                PuyoInterface puyo = grid.getPuyo(x, y);
                if (puyo != null && grid.getPuyo(x, y + 1) == null && puyo.getDeathClock().isEmpty()) {
                    grid.removePuyo(x, y);
                    puyo.moveDown();
                    grid.addPuyo(puyo, x, y + 1);
                }
            }
        }
    }
}