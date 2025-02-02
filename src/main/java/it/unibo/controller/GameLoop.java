//FEDE

package it.unibo.controller;

import it.unibo.view.GameView;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.PauseModel;
import it.unibo.model.StatusModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.Timer;

public class GameLoop implements ActionListener {
    private final GameView gameView;

    private final PauseModel pauseModel;

    private final StatusModel statusModel;

    private Timer gameTimer;

    private static final long min_delay = 1000 / 30; // 30 FPS
    private long lastTime;
    private final Set<TickListenerInterface> tickListeners;

    public GameLoop(GameView gameView, PauseModel pauseModel, StatusModel statusModel,
            Set<TickListenerInterface> tickListeners) {
        this.gameView = gameView;
        this.pauseModel = pauseModel;
        this.statusModel = statusModel;
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

    // Metodo per aggiornare lo stato del gioco
    private void update() {
        if (pauseModel.getPause()) {
            return;
        }
        if (statusModel.isGameEnded()) {
            return;
        }
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