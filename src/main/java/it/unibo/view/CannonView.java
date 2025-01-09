package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CannonView extends JPanel {

    private  Image cannonImage;
    private  int cannonWidth;
    private  int cannonHeight;


    public CannonView(final String imagePath) {
    
        final URL imageUrl = getClass().getClassLoader().getResource("images/" + imagePath);

        if (imageUrl == null) {
            System.err.println("Immagine non trovata: " + imagePath);
            return;
        }

        this.cannonImage = new ImageIcon(imageUrl).getImage();
        this.cannonHeight = cannonImage.getWidth(null);
        this.cannonWidth = cannonImage.getHeight(null);
    }  

    public final void draw(final Graphics g, final int x, final int y) {
        g.drawImage(cannonImage, x, y, null );
    }

    // Metodo per ottenere la larghezza del cannone
    public int getCannonWidth() {
        return this.cannonWidth; 
    }

    public int getCannonHeight() {
        return this.cannonHeight;
    }
    
}
