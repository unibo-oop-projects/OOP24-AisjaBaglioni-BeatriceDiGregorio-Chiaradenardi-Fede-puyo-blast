package it.unibo.controller;

import java.util.Optional;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.interfaces.PuyoInterface;

public class FreezeController implements TickListenerInterface{
    private final Grid grid;
    //valore atteso di freeze al secondo: probability * 30
    private static final double FREEZE_PROBABILITY = 0.0003;
    private static final int FREEZE_DURATION = 30*20;


    public FreezeController(Grid grid){
        this.grid = grid;
    }
}
