package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CannonView extends JPanel {
    private JLabel cannonLabel;
    private BufferedImage cannonImage;
    private int cannonWidth;
    private int cannonHeight;
    private int offsetX;
    private int offsetY;

    public CannonView() {
        this.setOpaque(false);
        this.setLayout(null);

        // Carica l'immagine come BufferedImage
        try {
            ImageIcon cannonIcon = new ImageIcon(getClass().getResource("/images/CannonImage.png"));
            cannonImage = new BufferedImage(
                cannonIcon.getIconWidth(),
                cannonIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
            );

            // Disegna l'immagine nel BufferedImage
            Graphics g = cannonImage.getGraphics();
            g.drawImage(cannonIcon.getImage(), 0, 0, null);
            g.dispose();

            // Calcola le dimensioni effettive (bounding box visibile)
            calculateCannonDimensions();


            // Imposta i bounds del pannello
            this.setPreferredSize(new Dimension(cannonWidth, cannonHeight));
            this.setBounds(0, 0, cannonWidth, cannonHeight);
            this.setVisible(true);
            this.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    // Metodo per calcolare le dimensioni effettive del cannone
    private void calculateCannonDimensions() {
        int width = cannonImage.getWidth();
        int height = cannonImage.getHeight();

        int minX = width, maxX = 0;
        int minY = height, maxY = 0;

        // Analizza i pixel per trovare i limiti del contenuto visibile
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = cannonImage.getRGB(x, y);

                // Controlla se il pixel non è completamente trasparente
                if ((pixel >> 24) != 0x00) { // Controlla il canale alpha
                    if (x < minX) minX = x;
                    if (x > maxX) maxX = x;
                    if (y < minY) minY = y;
                    if (y > maxY) maxY = y;
                }
            }
        }

        // Calcola la larghezza e l'altezza effettive
        cannonWidth = maxX - minX + 1;
        cannonHeight = maxY - minY + 1;

        offsetX = minX;
        offsetY = minY;

        System.out.println("Bounding box visibile: " + cannonWidth + "x" + cannonHeight);
        System.out.println("Offset: " + offsetX + ", " + offsetY);
    }


    // Metodo che aggiorna la posizione del cannone
    public void updateCannonPosition() {
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        // Debug: Verifica bounds e dimensioni del pannello
        System.out.println("Dimensioni pannello: " + panelWidth + "x" + panelHeight);
        System.out.println("Dimensioni cannone: " + cannonWidth + "x" + cannonHeight);

        // Posiziona il cannone al centro e sopra il bordo inferiore
        int x = (panelWidth - cannonWidth) / 2;
        int y = panelHeight - cannonHeight - 50;
        // Imposta i bounds per cannonView
        setBounds(x, y, cannonWidth, cannonHeight); // Usa setBounds per posizionare e ridimensionare
        revalidate(); // Rende la nuova posizione valida
        repaint(); // Forza il ridisegno del cannone
    }


    // Metodo per aggiornare l'angolo del cannone
    public void updateCannonAngle(int angle) {
        // logica per aggiornare la rotazione dell'immagine
    }

    // Override di paintComponent per debug
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // **Debug temporaneo per verificare il rendering**
        g.setColor(Color.RED); 
        g.fillRect(0, 0, getWidth(), getHeight());

        // Disegna l'immagine visibile usando l'offset calcolato
        if (cannonImage != null) {
            g.drawImage(
                cannonImage,
                -offsetX, // Sposta l'immagine in modo che il contenuto visibile inizi a (0, 0)
                -offsetY,
                null
            );
        }
    }

    // Metodo per disegnare il cannone
    public void paintCannon(Graphics2D g2d, int panelWidth, int panelHeight) {
        int cannonWidth = cannonLabel.getIcon().getIconWidth();
        int cannonHeight = cannonLabel.getIcon().getIconHeight();

        // Posiziona il cannone al centro inferiore del pannello
        int xPos = (panelWidth - cannonWidth) / 2;
        int yPos = panelHeight - cannonHeight - 20; // Un po' distanziato dal bordo inferiore

        g2d.translate(xPos, yPos); // Muovi le coordinate per centrare il cannone
        cannonLabel.paint(g2d); // Disegna il cannone
        g2d.translate(-xPos, -yPos); // Ripristina la posizione originale
    }

    // Metodo per ottenere la larghezza del cannone
    public int getCannonWidth() {
        return cannonWidth; 
    }

    public int getCannonHeight() {
        return cannonHeight;
    }
    
}
