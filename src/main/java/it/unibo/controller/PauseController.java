//fede

package it.unibo.controller;

import java.awt.event.ActionListener;

import it.unibo.model.PauseModel;

public class PauseController{
    private final PauseModel model;

    public PauseController(PauseModel model){
        this.model = model;
    }


    public void setPause(){
        this.model.changePause();
    }
}
