package it.unibo.controller.interfaces;

import it.unibo.view.GameView;

public interface GameLoopInterface {
    
    public void startGame();
    
    public void stopGame();
    
    public void addTickListener(TickListenerInterface tickListener);
    
    public void setGameView(GameView gameView);
}

