package it.unibo.controller;

import java.awt.event.ActionListener;

import it.unibo.model.PauseModel;

public class PauseController implements ActionListener {
    private final PauseModel model;

    public PauseController(PauseModel model){
        this.model = model;
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        this.model.changePause();
    }
}
