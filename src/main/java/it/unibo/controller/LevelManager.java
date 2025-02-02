//chiara
package it.unibo.controller;

import java.util.HashMap;
import java.util.Map;

public class LevelManager {
    private final Map<Integer, LevelConfig> levels;
    private int currentLevel;

    public LevelManager() {
        this.levels = new HashMap<>();
        this.currentLevel = 1;

        levels.put(1, new LevelConfig(33, 1)); // livello 1: 1 Puyo ogni 3 secondi
        levels.put(2, new LevelConfig(33, 2)); // livello 2: 2 Puyo ogni 2 secondi
        levels.put(3, new LevelConfig(33, 3)); // livello 3: 3 Puyo ogni 1 secondo
    }

    public LevelConfig getCurrentLevelConfig() {
        return levels.getOrDefault(currentLevel, new LevelConfig(33, 1));
    }

    public static class LevelConfig {
        private final int delay;
        private final int puyoCount;

        public LevelConfig(int delay, int puyoCount) {
            this.delay = delay;
            this.puyoCount = puyoCount;
        }

        public int getDelay() {
            return delay;
        }

        public int getPuyoCount() {
            return puyoCount;
        }
    }

    public LevelConfig getLevelConfig(int level) {
        return levels.getOrDefault(level, new LevelConfig(33, 1)); // default al livello 1
    }

    public void resetLevel(int level) {
        LevelConfig currentConfig = getLevelConfig(level); // Ottieni la configurazione del livello
        System.out.println("Ripristinato il livello " + level + " con delay: " + currentConfig.getDelay()
                + " e PuyoCount: " + currentConfig.getPuyoCount());
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

}
