package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuRules extends JPanel {
    private final JButton backButton = new JButton("Indietro");
    private Image backgroundImage; // Immagine di sfondo
    //private Image decoration;

    public MenuRules() {
        this.setLayout(new BorderLayout());

        // Caricamento dell'immagine di sfondo
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/images/menurules.jpg")).getImage();
           // decoration = new ImageIcon(getClass().getResource("/images/puyopers.png")).getImage();
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dell'immagine");
            e.printStackTrace();
        }

        JTextArea rulesText = new JTextArea(
            "Regole del Gioco:\n" +
            "- Combina almeno 2 Puyo dello stesso colore per eliminarli.\n" +
            "- Usa il cannone per lanciare i Puyo nella griglia.\n\n" +
            "Difficoltà dei livelli: \n" +
            "- Livello 1: 1 pallina ogni 3 secondi \n" +
            "- Livello 2: 2 palline ogni 2 secondi \n" +
            "- Livello 3: 3 palline ogni secondo \n\n" +
            "Comandi:\n" +
            "- Frecce direzionali dx e sx per spostare il cannone.\n" +
            "- Barra spaziatrice per sparare."
        );
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Segoe UI", Font.BOLD, 18));  // Testo in grassetto
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setOpaque(false);
        rulesText.setForeground(Color.BLACK);  // Testo in nero
        rulesText.setCaretColor(Color.BLACK);
        rulesText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(rulesText);
        scrollPane.setPreferredSize(new Dimension(650, 300));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Pannello inferiore con il pulsante "Indietro"
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        styleButton(backButton, new Color(50, 130, 255));

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.add(backButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Stile personalizzato per i pulsanti.
     */
    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Roboto", Font.BOLD, 24));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setPreferredSize(new Dimension(300, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setMargin(new Insets(15, 40, 15, 40));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
        });

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Override di paintComponent per disegnare lo sfondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Disegna l'immagine di sfondo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        
        // Disegna l'immagine decorativa sopra il bottone, scalata dinamicamente
        /*
        if (decoration != null) {
            // Definisci la scala dell'immagine rispetto alle dimensioni del pannello
            int scaledWidth = (int) (getWidth() * 0.5);  // 50% della larghezza del pannello
            int scaledHeight = (int) (getHeight() * 0.2); // 20% dell'altezza del pannello

            // Calcola la posizione per centrare l'immagine
            int x = (getWidth() - scaledWidth) / 2;
            int y = getHeight() - scaledHeight - (getHeight() / 10); // Sopra il bottone

            // Disegna l'immagine con la nuova scala
            g.drawImage(decoration, x, y, scaledWidth, scaledHeight, this);
        }
        */
    }
}
