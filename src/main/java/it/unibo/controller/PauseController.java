//fede

package it.unibo.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.model.PauseModel;

public class PauseController implements KeyListener {
    private final PauseModel model;

    public PauseController(PauseModel model){
        this.model = model;
    }


    public void togglePause(){
        this.model.togglePause();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P){
            togglePause();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
