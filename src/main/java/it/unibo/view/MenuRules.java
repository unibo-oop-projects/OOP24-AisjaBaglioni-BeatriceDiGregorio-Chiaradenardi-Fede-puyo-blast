//chiara
package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuRules extends JPanel {
    private final JButton backButton = new JButton("Indietro");

    public MenuRules() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(28, 28, 28));  

        JTextArea rulesText = new JTextArea(
            "Regole del Gioco:\n" +
            "- Combina almeno 2 Puyo dello stesso colore per eliminarli.\n" +
            "- Usa il cannone per lanciare i Puyo nella griglia.\n\n" +
            "Comandi:\n" +
            "- Frecce direzionali dx e sx per spostare il cannone.\n" +
            "- Barra spaziatrice per sparare."
        );
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Segoe UI", Font.PLAIN, 18));  
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setBackground(new Color(28, 28, 28));  
        rulesText.setForeground(new Color(200, 200, 200));  
        rulesText.setCaretColor(new Color(255, 255, 255));  
        rulesText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  

        JScrollPane scrollPane = new JScrollPane(rulesText);
        scrollPane.setPreferredSize(new Dimension(650, 300));  
        scrollPane.setBorder(BorderFactory.createEmptyBorder());  

        //pannello inferiore con il pulsante "Indietro"
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(28, 28, 28));  
        styleButton(backButton, new Color(70, 130, 180)); 

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));  
        bottomPanel.add(backButton);

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
        button.setPreferredSize(new Dimension(160, 50));  
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));  
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));  
            }
        });
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
