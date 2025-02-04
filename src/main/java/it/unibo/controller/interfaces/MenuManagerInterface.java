package it.unibo.controller.interfaces;

import it.unibo.controller.LevelManager;

public interface MenuManagerInterface {
    void start();
    void switchToMenuView();
    void switchToRulesView();
    void showLevelPopup(String level, LevelManager.LevelConfig config);
}
