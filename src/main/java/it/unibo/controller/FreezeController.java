//FEDE

package it.unibo.controller;

import java.util.Optional;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.interfaces.PuyoInterface;

public class FreezeController implements TickListenerInterface {
    private final Grid grid;
    // valore atteso di freeze al secondo: probability * 30
    private static final double FREEZE_PROBABILITY = 0.0003;
    private static final int FREEZE_DURATION = 30 * 20;

    public FreezeController(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void onTick() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                PuyoInterface puyo = grid.getPuyo(col, row);
                if (puyo == null || puyo.getDeathClock().isPresent()) {
                    continue;
                }
                if (puyo.getFreezeClock().isPresent()) {
                    int newFreezeClock = puyo.getFreezeClock().get() - 1;
                    if (newFreezeClock <= 0) {
                        puyo.setFreezeClock(Optional.empty());
                    } else {
                        puyo.setFreezeClock(Optional.of(newFreezeClock));
                    }
                } else if (Math.random() < FREEZE_PROBABILITY) {
                    puyo.setFreezeClock(Optional.of(FREEZE_DURATION));
                }
            }
        }
    }
}
