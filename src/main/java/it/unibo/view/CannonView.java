package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CannonView extends JPanel {

    private  Image cannonImage;
    private  int cannonWidth;
    private  int cannonHeight;
    private int cannonX;
    private int cannonY;


    public CannonView(final String imagePath) {
    
        final URL imageUrl = getClass().getClassLoader().getResource("images/" + imagePath);

        if (imageUrl == null) {
            System.err.println("Immagine non trovata: " + imagePath);
            return;
        }

        this.cannonImage = new ImageIcon(imageUrl).getImage();
        this.cannonHeight = cannonImage.getWidth(null);
        this.cannonWidth = cannonImage.getHeight(null);
        this.cannonX = 0;
        this.cannonY = 0;
    }  

    public final void draw(final Graphics g) {
        g.drawImage(cannonImage, cannonX, cannonY, null );
    }

    public void setCannonPosition(int newX, int newY){
        this.cannonX = newX; 
        this.cannonY = newY;

    }

    // Metodo per ottenere la larghezza del cannone
    public int getCannonWidth() {
        return this.cannonWidth; 
    }

    public int getCannonHeight() {
        return this.cannonHeight;
    }
    
}
