package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import it.unibo.model.Scale;

public class MenuRules extends JPanel {
    private final JButton backButton;
    private Image backgroundImage; // Immagine di sfondo
    private Image decoration1;
    private Image decoration2;
    private final int scale; // Variabile per la scala

    public MenuRules(Scale scaleObj) {
        this.scale = scaleObj.getScale(); // Ottieni la scala dall'oggetto Scale
        this.setLayout(new BorderLayout());

        // Caricamento delle immagini
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/images/rulesimg.jpg")).getImage();
            decoration1 = new ImageIcon(getClass().getResource("/images/pers1.png")).getImage();
            decoration2 = new ImageIcon(getClass().getResource("/images/pers2.png")).getImage();
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dell'immagine");
            e.printStackTrace();
        }

        // Testo delle regole con dimensioni scalate
        JTextArea rulesText = new JTextArea(
            "Regole del Gioco:\n" +
            "- Usa il cannone per sparare ai puyo nella griglia.\n" +
            "- Combina almeno 2 Puyo dello stesso colore per aumentare il moltiplicatore punteggio.\n" +
            "- I puyo potrebbero auto-congelarsi. Durante il congelamento, non è possibile distruggerli. Se la barra di \n"+
            "caricamento del cannone è piena, sarà possibile sparare un “colpo termico” speciale per scongelarli.\n\n"+
            "Difficoltà dei livelli: \n" +
            "- Ci sono 3 livelli dove la difficoltà aumenta avendo più palline che cadono insieme più velocemente\n\n"+
            "Comandi:\n" +
            "- Frecce direzionali per spostare il mirino.\n" +
            "- Barra spaziatrice per sparare.\n"+
            "Sii veloce! Il gioco finisce se la griglia si riempie.\n"+
            "Devi raggiungere almeno una stella per passare il livello."
        );
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, scale / 40));  
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setOpaque(false);
        rulesText.setForeground(Color.BLACK);
        rulesText.setCaretColor(Color.BLACK);
        rulesText.setBorder(BorderFactory.createEmptyBorder(scale / 35, scale / 35, scale / 35, scale / 35));

        JScrollPane scrollPane = new JScrollPane(rulesText);
        scrollPane.setPreferredSize(new Dimension(scale, scale / 2));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Pannello inferiore con il pulsante "Indietro"
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);

        backButton = new JButton("Indietro");
        styleButton(backButton, new Color(50, 130, 255), scale);

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, scale / 35, scale / 35));
        bottomPanel.add(backButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Stile personalizzato per i pulsanti con supporto a `Scale`.
     */
    private void styleButton(JButton button, Color backgroundColor, int scale) {
        button.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, scale / 30));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setPreferredSize(new Dimension(scale / 2, scale /15 ));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(scale / 35, scale / 18, scale / 35, scale / 25));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
        });

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Override di paintComponent per disegnare lo sfondo e le immagini decorative
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Disegna lo sfondo
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Disegna le immagini decorative scalate
        if (decoration1 != null && decoration2 != null) {
            double scaleX = 0.3; // 30% della larghezza del pannello
            double scaleY = 0.4; // 40% dell'altezza del pannello

            int scaledWidth = scale / 3;
            int scaledHeight = scale / 2;

            // Creazione trasformazioni di scala
            AffineTransform transform1 = new AffineTransform();
            transform1.translate(scale / 30, getHeight() - scaledHeight + (scale/7)); 
            transform1.scale(0.2, 0.3);

            AffineTransform transform2 = new AffineTransform();
            transform2.translate(getWidth() - scaledWidth + (scale / 10), getHeight() - scaledHeight + (scale / 20));
            transform2.scale(scaleX, scaleY);

            // Disegno delle immagini scalate
            g2d.drawImage(decoration1, transform2, this);
            g2d.drawImage(decoration2, transform1, this);
        }
    }
}
