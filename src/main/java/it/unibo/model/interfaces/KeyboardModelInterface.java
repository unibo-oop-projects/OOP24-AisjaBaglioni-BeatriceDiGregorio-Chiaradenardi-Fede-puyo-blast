package it.unibo.model.interfaces;

public interface KeyboardModelInterface {
    void keyDown(Integer key);
    void keyUp(Integer key);
    boolean isKeyPressed(Integer key);
}
