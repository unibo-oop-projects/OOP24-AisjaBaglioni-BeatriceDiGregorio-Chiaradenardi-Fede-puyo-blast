package it.unibo.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.controller.PauseController;
import it.unibo.model.PauseModel;

public class PauseView {
    private JButton pause_button;
    private PauseModel model;
    
    public PauseView(PauseModel model, JPanel p) {
        this.pause_button = new JButton();
        p.add(this.pause_button);
        this.model = new PauseModel();
        PauseController ctrl = new PauseController(this.model);
        this.pause_button.addActionListener(ctrl);
    }

    final public void draw() {
        if (model.getPause()) {
            this.pause_button.setText("Play");
        } else {
            this.pause_button.setText("Pause");
        }
    }
}
