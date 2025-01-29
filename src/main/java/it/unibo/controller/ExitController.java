package it.unibo.controller;

import it.unibo.model.ExitModel;

public class ExitController {
    private ExitModel model;
    private ScreenManager screenManager;

    public ExitController(ExitModel model, ScreenManager screenManager) {
        this.model = model;
        this.screenManager = screenManager;
    }

    public void onExitClicked() {
        model.setExitClicked(true);
        System.out.println("Hai cliccato Main Menu");
        screenManager.switchToMenuView();
    }
}
