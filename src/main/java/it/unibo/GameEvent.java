

package it.unibo;

import java.util.EventObject;

import it.unibo.controller.LevelManager;

// Used to notify the listener when an event occurs 
public class GameEvent extends EventObject {
    public LevelManager.LevelConfig levelConfig;

    public GameEvent(Object source) {
        super(source);
    }

    public GameEvent(Object source, LevelManager.LevelConfig levelConfig) {
        super(source);
        this.levelConfig = levelConfig;
    }
}
