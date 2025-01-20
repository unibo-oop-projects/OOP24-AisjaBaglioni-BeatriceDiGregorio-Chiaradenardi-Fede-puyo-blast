package it.unibo.controller;

import it.unibo.controller.interfaces.ProgressBarControllerInterface;
import it.unibo.model.ProgressBarModel;
import it.unibo.view.ProgressBarView;
import javax.swing.*;

public class ProgressBarController implements ProgressBarControllerInterface {
    private final ProgressBarModel progressBarModel;
    private final ProgressBarView progressBarView;
    private Timer progressBarTimer;

    public ProgressBarController(final ProgressBarView view) {
        this.progressBarModel = new ProgressBarModel();
        this.progressBarView = view;

        progressBarTimer = new Timer(250, e -> {
            progressBarModel.incrementProgress(1);
            progressBarView.setProgress(progressBarModel.getProgress());
        });
    }

    @Override
    final public void startProgress() {
        // Avvia il timer per l'aggiornamento della progress bar
        progressBarTimer.start();
    }

    @Override
    final public void setProgress(final int progress) {
        progressBarModel.setProgress(progress); // Aggiorna il modello
        progressBarView.setProgress(progressBarModel.getProgress()); // Aggiorna la vista
    }

    @Override
    final public void stopProgress() {
        progressBarTimer.stop();
    }

    @Override
    final public void resetProgress() {
        progressBarModel.resetCharge();
        progressBarView.repaint();
    }

    @Override
    final public void resetProgressBar() {
        stopProgress();
        resetProgress();
    }

    @Override
    final public int getProgress() {
        return progressBarModel.getProgress();
    }

}
