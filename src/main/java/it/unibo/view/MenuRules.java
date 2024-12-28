package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuRules extends JPanel {
    private final JButton backButton = new JButton("Indietro");

    public MenuRules() {
        this.setLayout(new BorderLayout());

        JTextArea rulesText = new JTextArea(
            "Regole del Gioco:\n" +
            "- Combina almeno 4 Puyo dello stesso colore per eliminarli.\n" +
            "- Usa il cannone per lanciare i Puyo nella griglia.\n\n" +
            "Comandi:\n" +
            "- Frecce direzionali per spostare.\n" +
            "- Barra spaziatrice per sparare."
        );
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Arial", Font.PLAIN, 16));
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(rulesText);

        //pulsante per tornare indietro
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
