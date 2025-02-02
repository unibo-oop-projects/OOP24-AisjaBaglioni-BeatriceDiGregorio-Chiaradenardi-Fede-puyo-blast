package it.unibo.model;

import javax.swing.*;
import it.unibo.model.interfaces.MenuInterface;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel implements MenuInterface {
    private final JButton startButton;
    private final JButton controlsButton;
    private final JComboBox<String> levelsDropdown;
    private Image backgroundImage; // Immagine di sfondo
    private final int scale; // Variabile per la scala

    public Menu(String[] levels, Scale scaleObj) {
        this.scale = scaleObj.getScale(); // Ottieni la scala dall'oggetto Scale
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(28, 28, 28));

        // Caricamento dell'immagine di sfondo
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/images/puyomenu.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dell'immagine");
            e.printStackTrace();
        }

        // Pannello centrale con layout verticale
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // Titolo (posizionato in basso a sinistra in blu scuro)
        JLabel titleLabel = new JLabel("PUYO POP: BLAST!");
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, scale / 17)); // Font scalato
        titleLabel.setForeground(new Color(51, 73, 112));

        JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, scale / 45, scale / 15));
        titleWrapper.setOpaque(false);
        titleWrapper.add(titleLabel);

        // Pannello per posizionare il titolo in basso
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(titleWrapper, BorderLayout.SOUTH);

        // Dropdown dei livelli (centrato a sinistra)
        levelsDropdown = new JComboBox<>(levels);
        levelsDropdown.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, scale / 35));
        levelsDropdown.setMaximumSize(new Dimension(scale / 3, scale / 15));
        levelsDropdown.setAlignmentX(Component.LEFT_ALIGNMENT);
        levelsDropdown.setBackground(new Color(57, 143, 192));
        levelsDropdown.setForeground(Color.BLACK);

        JLabel levelLabel = new JLabel("Seleziona Livello:");
        levelLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, scale / 35));
        levelLabel.setForeground(Color.BLACK);
        levelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Pulsanti scalati
        startButton = new JButton("Inizia Gioco");
        controlsButton = new JButton("Comandi");
        styleButton(startButton, new Color(57, 143, 191), scale);
        styleButton(controlsButton, new Color(57, 143, 191), scale);

        // Componenti nel pannello centrale
        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 7)));
        centerPanel.add(levelLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 35)));
        centerPanel.add(levelsDropdown);
        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 20)));
        centerPanel.add(startButton);
        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 35)));
        centerPanel.add(controlsButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button, Color backgroundColor, int scale) {
        button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, scale / 30));
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(scale / 2, scale / 10));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Effetto hover per i pulsanti (scurisce il colore quando il mouse entra)
        button.addMouseListener(new MouseAdapter() {
            private Color hoverColor = backgroundColor.darker();

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(true);
    }

    // Override del metodo paintComponent per disegnare l'immagine di sfondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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
