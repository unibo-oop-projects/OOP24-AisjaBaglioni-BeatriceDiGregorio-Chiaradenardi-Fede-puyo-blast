package it.unibo.controller;
//Traduce gli input del giocatore (tasti, mouse) in azioni. (NO FEDE)

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.model.KeyboardModel;

public class KeyboardController implements KeyListener{
    private final KeyboardModel keyboardModel;

    public KeyboardController(KeyboardModel keyboardModel) {
        this.keyboardModel = keyboardModel;
    }

    // Metodo per gestire l'input da tastiera
    @Override
    final public void keyPressed(KeyEvent e) {
        this.keyboardModel.keyDown(e.getKeyCode());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyboardModel.keyUp(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}