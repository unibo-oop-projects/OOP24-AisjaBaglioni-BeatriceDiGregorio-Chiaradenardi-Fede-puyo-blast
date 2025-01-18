package it.unibo.controller.interfaces;

public interface ProgressBarControllerInterface {
    void startProgress();

    void setProgress(int progress);

    void stopProgress();

    void resetProgress();

    void resetProgressBar();

    int getProgress();
}
