//FEDE
//carica e visualizza lo sfondo del gioco
package it.unibo.view;

import it.unibo.view.interfaces.BackgroundInterface;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Background implements BackgroundInterface {
    private Image backgroundImage;

    public Background(String imagePath) {
        // Carica l'immagine dalla cartella resources/images
        URL imageUrl = getClass().getClassLoader().getResource("images/" + imagePath);

        if (imageUrl == null) {
            System.err.println("Immagine non trovata: " + imagePath);
            return;
        }

        this.backgroundImage = new ImageIcon(imageUrl).getImage();
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        g.drawImage(backgroundImage, 0, 0, width, height, null);
    }
}
