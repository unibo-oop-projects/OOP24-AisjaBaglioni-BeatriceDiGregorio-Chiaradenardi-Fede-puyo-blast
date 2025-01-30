//FEDE

package it.unibo.controller;

import it.unibo.view.GameView;
import it.unibo.controller.interfaces.TickListenerInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.Timer;

public class GameLoop implements ActionListener {
    private final GameView gameView;

    private boolean paused;
    private Timer gameTimer;

    private static final long min_delay = 1000 / 30; // 30 FPS
    private long lastTime;
    private final Set<TickListenerInterface> tickListeners;

    public GameLoop(GameView gameView, Set<TickListenerInterface> tickListeners) {
        this.gameView = gameView;
        this.paused = false;
        this.tickListeners = tickListeners;
        this.gameTimer = new Timer(1, this);
    }

    // metodo per avviare il gioco
    public void startGame() {
        this.lastTime = System.currentTimeMillis();
        this.gameTimer.start();
    }

    // metodo per aggiungere un listener
    public void addTickListener(TickListenerInterface tickListener) {
        this.tickListeners.add(tickListener);
    }

    // metodo per rimuovere un listener
    public void removeTickListener(TickListenerInterface tickListener) {
        this.tickListeners.remove(tickListener);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    // alterna lo stato di pausa
    public void togglePaused() {
        this.paused = !this.paused;
    }

    // Metodo per aggiornare lo stato del gioco
    private void update() {
        // notifica tutti i listener
        for (TickListenerInterface tickListener : tickListeners) {
            tickListener.onTick();
        }
    }

    // Metodo per ridisegnare la grafica
    private void render() {
        gameView.repaint();
    }

    // fa update e render
    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= min_delay) {
            lastTime = currentTime;
            update();
        }
        render();
    }

}
