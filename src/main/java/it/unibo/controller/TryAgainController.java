package it.unibo.controller;

import it.unibo.GameEvent;
import it.unibo.GameListener;
import it.unibo.controller.interfaces.TryAgainControllerInterface;

public class TryAgainController implements TryAgainControllerInterface{
    private final LevelManager.LevelConfig levelconfig;
    private final GameListener listener;
    private final GameLoop gameLoop;

    public TryAgainController(LevelManager.LevelConfig levelconfig, GameListener listener, GameLoop gameLoop) {
        this.levelconfig = levelconfig;
        this.listener = listener;
        this.gameLoop = gameLoop;
    }

    @Override
    public void handleClick() {
        gameLoop.stopGame();
        GameEvent e = new GameEvent(this, levelconfig);
        listener.startGame(e);
    }
}