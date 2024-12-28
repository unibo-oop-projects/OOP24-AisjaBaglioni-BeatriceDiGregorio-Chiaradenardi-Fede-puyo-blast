package it.unibo.model;

import javax.swing.*;
import it.unibo.model.interfaces.MenuInterface;
import java.awt.*;

public class Menu extends JFrame implements MenuInterface {
    private JButton startButton = new JButton("Inizia Gioco");
    private JButton controlsButton = new JButton("Comandi");
    private JComboBox<String> levelsDropdown;

    public Menu(String[] levels) {
        this.setTitle("Puyo Pop - Menù Iniziale");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());

        //dropdown per i livelli
        levelsDropdown = new JComboBox<>(levels);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Seleziona Livello:"));
        centerPanel.add(levelsDropdown);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(startButton);
        bottomPanel.add(controlsButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public JButton getStartButton() {
        return startButton;
    }

    @Override
    public JButton getControlsButton() {
        return controlsButton;
    }

    @Override
    public String getSelectedLevel() {
        return (String) levelsDropdown.getSelectedItem();
    }

    @Override
    public void display() {
        this.setVisible(true);
    }
}
