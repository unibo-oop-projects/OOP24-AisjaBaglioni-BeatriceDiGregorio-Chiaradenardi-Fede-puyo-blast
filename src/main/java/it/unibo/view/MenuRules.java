package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class MenuRules extends JPanel {
    private final JButton backButton = new JButton("Indietro");
    private Image backgroundImage; // Immagine di sfondo
    private Image decoration1;
    private Image decoration2;

    public MenuRules() {
        this.setLayout(new BorderLayout());

        // Caricamento dell'immagine di sfondo
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/images/rulesimg.jpg")).getImage();
            decoration1 = new ImageIcon(getClass().getResource("/images/pers1.png")).getImage();
            decoration2 = new ImageIcon(getClass().getResource("/images/pers2.png")).getImage();
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dell'immagine");
            e.printStackTrace();
        }

        JTextArea rulesText = new JTextArea(
            "Regole del Gioco:\n" +
            "- Usa il cannone per sparare ai puyo nella griglia.\n" +
            "- Combina almeno 2 Puyo dello stesso colore per aumentare il moltiplicatore punteggio.\n\n" +
            "Difficoltà dei livelli: \n" +
            "- Ci sono 3 livelli dove la difficoltà aumenta avendo più palline che cadono insieme più velocemente\n\n"+
            "Comandi:\n" +
            "- Frecce direzionali per spostare il mirino.\n" +
            "- Barra spaziatrice per sparare.\n"+
            "Sii veloce! Il gioco finisce se la griglia si riempie.\n"+
            "Devi raggiungere almeno una stella per passare il livello."
        );
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));  // Testo in grassetto
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
        button.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setPreferredSize(new Dimension(300, 60));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setMargin(new Insets(15, 40, 15, 40));

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

    // Override di paintComponent per disegnare lo sfondo
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    
    // Disegna lo sfondo
    if (backgroundImage != null) {
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    // Disegna le immagini decorative in posizioni opposte
    if (decoration1 != null && decoration2 != null) {
        double scaleX = 0.3; // 30% della larghezza del pannello
        double scaleY = 0.4; // 40% dell'altezza del pannello

        int scaledWidth = (int) (getWidth() * scaleX);
        int scaledHeight = (int) (getHeight() * scaleY);

        // Creazione trasformazioni di scala
        AffineTransform transform1 = new AffineTransform();
        transform1.translate(10, getHeight() - scaledHeight - (getHeight() / 16)); 
        transform1.scale(scaleX, scaleY);

        AffineTransform transform2 = new AffineTransform();
        transform2.translate(getWidth() - scaledWidth + 50, getHeight() - scaledHeight - (getHeight() / 11));
        transform2.scale(scaleX, scaleY);

        // Disegno delle immagini scalate
        g2d.drawImage(decoration1, transform2, this);
        g2d.drawImage(decoration2, transform1, this);
    }
}

}
