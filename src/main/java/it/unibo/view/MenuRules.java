package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuRules extends JPanel {
    private final JButton backButton = new JButton("Indietro");

    public MenuRules() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(28, 28, 28));  // Colore scuro per un look elegante

        // Testo delle regole
        JTextArea rulesText = new JTextArea(
            "Regole del Gioco:\n" +
            "- Combina almeno 2 Puyo dello stesso colore per eliminarli.\n" +
            "- Usa il cannone per lanciare i Puyo nella griglia.\n\n" +
            "Comandi:\n" +
            "- Frecce direzionali dx e sx per spostare il cannone.\n" +
            "- Barra spaziatrice per sparare."
        );
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Segoe UI", Font.PLAIN, 18));  // Font moderno
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setBackground(new Color(28, 28, 28));  // Colore di sfondo scuro
        rulesText.setForeground(new Color(200, 200, 200));  // Colore del testo chiaro
        rulesText.setCaretColor(new Color(255, 255, 255));  // Colore del cursore
        rulesText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Spaziatura interna senza bordi

        // Rimuovere completamente il bordo della JScrollPane
        JScrollPane scrollPane = new JScrollPane(rulesText);
        scrollPane.setPreferredSize(new Dimension(650, 300));  // Dimensione fissa per lo scroll
        scrollPane.setBorder(BorderFactory.createEmptyBorder());  // Rimuove il bordo della JScrollPane

        // Pannello inferiore con il pulsante "Indietro"
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(28, 28, 28));  // Colore scuro coerente

        // Personalizza il pulsante per un look elegante
        styleButton(backButton, new Color(70, 130, 180)); // Blu elegante per il pulsante

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));  // Spaziatura tra il pulsante e il bordo
        bottomPanel.add(backButton);

        // Organizza i contenuti nel pannello principale
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Stile personalizzato per i pulsanti.
     */
    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setPreferredSize(new Dimension(160, 50));  // Dimensione più grande per un look più accattivante
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // Cambia il cursore per un'interazione più fluida

        // Aggiungi un effetto hover per il pulsante
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));  // Colore di hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));  // Colore originale
            }
        });
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
