//AISJA

package it.unibo.model;

import java.util.HashSet;

import it.unibo.model.interfaces.KeyboardModelInterface;

public class KeyboardModel implements KeyboardModelInterface {
    private final HashSet<Integer> pressedKeys;

    public KeyboardModel() {
        this.pressedKeys = new HashSet<>();
    }

    @Override
    public void keyDown(Integer key) {
        this.pressedKeys.add(key);
    }

    @Override
    public void keyUp(Integer key) {
        this.pressedKeys.remove(key);
    }

    @Override
    public boolean isKeyPressed(Integer key) {
        return this.pressedKeys.contains(key);
    }
}
