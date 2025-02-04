package it.unibo.controller;

import it.unibo.GameEvent;
import it.unibo.GameListener;
import it.unibo.controller.interfaces.ExitControllerInterface;

public class ExitController implements ExitControllerInterface{
    private final GameListener listener;
    private final GameLoop gameLoop;

    public ExitController(GameListener listener, GameLoop gameLoop) {
        this.listener = listener;
        this.gameLoop = gameLoop;
    }

    @Override
    public void onExitClicked() {
        gameLoop.stopGame();
        GameEvent e = new GameEvent(this);
        listener.goToMainMenu(e);
        System.out.println("Hai cliccato Main Menu");
    }
}