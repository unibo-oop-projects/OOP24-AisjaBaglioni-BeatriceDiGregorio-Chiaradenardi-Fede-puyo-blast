package it.unibo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.controller.LevelManager;

class LevelManagerTest {
    private LevelManager levelManager;

    @BeforeEach
    void setUp() {
        levelManager = new LevelManager(); // Inizializza un nuovo LevelManager prima di ogni test
    }

    @Test
    void testGetCurrentLevelConfig() {
        LevelManager.LevelConfig config = levelManager.getCurrentLevelConfig();
        assertNotNull(config, "La configurazione del livello non dovrebbe essere null");
        assertEquals(25, config.getDelay(), "Il delay del livello iniziale dovrebbe essere 25");
        assertEquals(1, config.getPuyoCount(), "Il numero di Puyo per il livello iniziale dovrebbe essere 1");
    }

    @Test
    void testGetLevelConfig() {
        LevelManager.LevelConfig level1 = levelManager.getLevelConfig(1);
        LevelManager.LevelConfig level2 = levelManager.getLevelConfig(2);
        LevelManager.LevelConfig level3 = levelManager.getLevelConfig(3);
        LevelManager.LevelConfig defaultLevel = levelManager.getLevelConfig(99); // Un livello non esistente

        assertEquals(25, level1.getDelay(), "Il livello 1 dovrebbe avere delay 25");
        assertEquals(1, level1.getPuyoCount(), "Il livello 1 dovrebbe avere 1 Puyo");

        assertEquals(30, level2.getDelay(), "Il livello 2 dovrebbe avere delay 30");
        assertEquals(2, level2.getPuyoCount(), "Il livello 2 dovrebbe avere 2 Puyo");

        assertEquals(30, level3.getDelay(), "Il livello 3 dovrebbe avere delay 30");
        assertEquals(3, level3.getPuyoCount(), "Il livello 3 dovrebbe avere 3 Puyo");

        assertEquals(30, defaultLevel.getDelay(), "Un livello non definito dovrebbe avere delay di default 30");
        assertEquals(1, defaultLevel.getPuyoCount(), "Un livello non definito dovrebbe avere 1 Puyo di default");
    }

    @Test
    void testGetCurrentLevel() {
        assertEquals(1, levelManager.getCurrentLevel(), "Il livello iniziale dovrebbe essere 1");
    }
}
