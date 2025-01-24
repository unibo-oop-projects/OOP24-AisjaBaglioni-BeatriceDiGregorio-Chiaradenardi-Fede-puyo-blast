//FEDE

package it.unibo.controller;

import it.unibo.model.GameState;
import it.unibo.model.Grid;
import it.unibo.view.GameView;
import it.unibo.controller.interfaces.TickListenerInterface;

import java.util.Set;

public class GameLoop implements Runnable {
    private final GameState gameState;
    private final Grid grid;
    private final GameView gameView;

    private boolean running;
    private boolean paused;
    private final int targetFPS = 60; // Frame rate desiderato

    private final Set<TickListenerInterface> tickListeners ;

    public GameLoop(GameState gameState, Grid grid, GameView gameView, Set<TickListenerInterface> tickListeners) {
        this.gameState = gameState;
        this.grid = grid;
        this.gameView = gameView;
        this.running = false;
        this.paused = false;
        this.tickListeners = tickListeners;


    }

    //metodo per aggiungere un listener
    public void addTickListener(TickListenerInterface tickListener){
        this.tickListeners.add(tickListener);
    }

    //metodo per rimuovere un listener
    public void removeTickListener(TickListenerInterface tickListener){
        this.tickListeners.remove(tickListener);
    }   

    // Avvia il ciclo di gioco
    public void start() {
        running = true;
        new Thread(this).start(); // Esegui il loop in un thread separato
    }

    // Ferma il ciclo di gioco
    public void stop() {
        running = false;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    //alterna lo stato di pausa
    public void togglePaused() {
        this.paused = !this.paused;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerFrame = 1_000_000_000.0 / targetFPS;

        while (running) {
            long now = System.nanoTime();
            if ((now - lastTime) >= nsPerFrame) {
                lastTime = now;

                if(!paused) {
                    // Aggiorna lo stato del gioco
                    update();

                    // Ridisegna la grafica
                    render();
                }else{
                    try {
                        Thread.sleep(100); // Riduce il consumo di CPU in pausa
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
            }

        }
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
}

