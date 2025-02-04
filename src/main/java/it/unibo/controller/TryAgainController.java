package it.unibo.controller;

import it.unibo.GameEvent;
import it.unibo.GameListener;

public class TryAgainController {
    private final LevelManager.LevelConfig levelconfig;
    private final GameListener listener;
    private final GameLoop gameLoop;

    public TryAgainController(LevelManager.LevelConfig levelconfig, GameListener listener, GameLoop gameLoop) {
        this.levelconfig = levelconfig;
        this.listener = listener;
        this.gameLoop = gameLoop;
    }

    public void handleClick() {
        System.out.println("Riavvio del gioco...");
        gameLoop.stopGame();
        GameEvent e = new GameEvent(this, levelconfig);
        listener.startGame(e);
    }
}