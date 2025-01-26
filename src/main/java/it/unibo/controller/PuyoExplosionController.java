// Aisja

package it.unibo.controller;

import java.util.Optional;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.interfaces.PuyoInterface;

public class PuyoExplosionController implements TickListenerInterface {
    private final Grid grid;

    public PuyoExplosionController(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void onTick() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                PuyoInterface puyo = grid.getPuyo(col, row);
                if (puyo != null && puyo.getDeathClock().isPresent()) {
                    int deathClock = puyo.getDeathClock().get();
                    deathClock--;
                    if (deathClock <= 0) {
                        grid.removePuyo(col, row);
                    } else {
                        puyo.setDeathClock(Optional.of(deathClock));
                    }
                }
            }
        }
    }
}
