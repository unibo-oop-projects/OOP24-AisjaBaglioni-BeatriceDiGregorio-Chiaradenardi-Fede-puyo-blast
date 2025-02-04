package it.unibo.controller.interfaces;

import it.unibo.controller.LevelManager.LevelConfig;

public interface LevelManagerInterface {
    LevelConfig getLevelConfig(int level);
    void resetLevel(int level);
    int getCurrentLevel();
    LevelConfig getCurrentLevelConfig();
}
