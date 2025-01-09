package it.unibo.controller.interfaces;

import java.awt.event.KeyEvent;


public interface InputHandlerInterface {

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);

    void keyTyped(KeyEvent e);
}
