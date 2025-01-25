//FEDE

package it.unibo.controller;

import it.unibo.model.GameState;
import it.unibo.model.Grid;
import it.unibo.view.GameView;
import it.unibo.controller.interfaces.TickListenerInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.Timer;

public class GameLoop implements ActionListener {
    private final GameState gameState;
    private final Grid grid;
    private final GameView gameView;

    private boolean running;
    private boolean paused;
    private Timer gameTimer;

    private static final int delay = 33; // 30 FPS 
    private final Set<TickListenerInterface> tickListeners;



    public GameLoop(GameState gameState, Grid grid, GameView gameView, Set<TickListenerInterface> tickListeners) {
        this.gameState = gameState;
        this.grid = grid;
        this.gameView = gameView;
        this.running = false;
        this.paused = false;
        this.tickListeners = tickListeners;
        this.gameTimer = new Timer(delay, this);
    }

    //metodo per avviare il gioco
    public void startGame() {
        this.gameTimer.start();
    }

    //metodo per aggiungere un listener
    public void addTickListener(TickListenerInterface tickListener){
        this.tickListeners.add(tickListener);
    }

    //metodo per rimuovere un listener
    public void removeTickListener(TickListenerInterface tickListener){
        this.tickListeners.remove(tickListener);
    }   

    

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    //alterna lo stato di pausa
    public void togglePaused() {
        this.paused = !this.paused;
    }

    // Metodo per aggiornare lo stato del gioco
    private void update() {
        if (!gameState.isGameOver()) {
            // Aggiorna la logica della griglia
            grid.updateGrid();

            //ntifica tutti i listener
            for (TickListenerInterface tickListener : tickListeners) {
                tickListener.onTick();
            }

            // Verifica condizioni di fine partita
            if (grid.isGridFull()) {
                gameState.setGameOver(true);
            }
        }
    }

    // Metodo per ridisegnare la grafica
    private void render() {
        gameView.repaint();
    }

    //fa update e render
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        render();
    }

    
}

