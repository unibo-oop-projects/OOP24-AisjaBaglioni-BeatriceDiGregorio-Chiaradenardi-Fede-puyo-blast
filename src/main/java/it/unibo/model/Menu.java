//chiara
package it.unibo.model;

import javax.swing.*;
import it.unibo.model.interfaces.MenuInterface;
import java.awt.*;

public class Menu extends JPanel implements MenuInterface {
    private final JButton startButton = new JButton("Inizia Gioco");
    private final JButton controlsButton = new JButton("Comandi");
    private final JComboBox<String> levelsDropdown;

    public Menu(String[] levels) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        //pannello centrale con layout verticale
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        //titolo
        JLabel titleLabel = new JLabel("PUYO POP");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //dropdown dei livelli
        levelsDropdown = new JComboBox<>(levels);
        levelsDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
        levelsDropdown.setMaximumSize(new Dimension(200, 40));
        levelsDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel levelLabel = new JLabel("Seleziona Livello:");
        levelLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //pulsanti
        styleButton(startButton, new Color(34, 139, 34));
        styleButton(controlsButton, new Color(70, 130, 180));

        //componenti nel pannello centrale
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        centerPanel.add(levelLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(levelsDropdown);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(startButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(controlsButton);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Stile personalizzato per i pulsanti.
     */
    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
}
