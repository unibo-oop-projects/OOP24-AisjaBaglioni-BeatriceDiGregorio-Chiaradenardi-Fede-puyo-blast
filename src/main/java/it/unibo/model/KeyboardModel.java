//AISJA


package it.unibo.model;

import java.util.HashSet;

public class KeyboardModel {
    private final HashSet<Integer> pressedKeys;

    public KeyboardModel() {
        this.pressedKeys = new HashSet<>();
    }

    public void keyDown(Integer key) {
        this.pressedKeys.add(key);
    }

    public void keyUp(Integer key) {
        this.pressedKeys.remove(key);
    }

    public boolean isKeyPressed(Integer key) {
        return this.pressedKeys.contains(key);
    }
}
