//chiara
package it.unibo.controller;

import it.unibo.controller.interfaces.PuyoDropperInterface;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuyoDropper implements PuyoDropperInterface, TickListenerInterface{
    private final Grid grid;
    private final GameView gameView;
    private final Random random;
    private final String[] colors = {"red", "blue", "green", "yellow", "purple", "cyan", "pink"};
    private final List<Puyo> puyosInGame; //per memorizzare i Puyo nella griglia
    private final LevelManager.LevelConfig levelConfig;
    private int ticksPassed;
    
    public PuyoDropper(Grid grid, GameView gameView, LevelManager.LevelConfig levelConfig) {
        this.grid = grid;
        this.gameView = gameView;
        this.random = new Random();
        this.puyosInGame = new ArrayList<>();
        this.levelConfig = levelConfig;
        this.ticksPassed = 0;
    }

    //metodo per generare un Puyo casuale e farlo cadere
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
    

    //trova la prima posizione disponibile in una colonna a partire dalla riga più bassa
    @Override
    public int findAvailableYPosition(int x) {
        for (int y = grid.getRows() - 1; y >= 0; y--) { //controlla dalla riga più bassa
            if (grid.getPuyo(x, y) == null) {
                return y; //restituisce la prima riga vuota (dall'alto verso il basso)
            }
        }
        return -1; //se non c'è spazio (colonna piena)
    }

    //logica di caduta del Puyo
    // logica di caduta dei Puyo
    @Override
    public void dropPuyo() {
        for (int y = grid.getRows() - 2; y >= 0; y--) {
            for (int x = 0; x < grid.getCols(); x++) {
                PuyoInterface puyo = grid.getPuyo(x, y);
                if (puyo != null && grid.getPuyo(x, y + 1) == null) {
                    grid.removePuyo(x, y);
                    puyo.moveDown();
                    grid.addPuyo(puyo, x, y + 1);
                }
            }
        }
}
