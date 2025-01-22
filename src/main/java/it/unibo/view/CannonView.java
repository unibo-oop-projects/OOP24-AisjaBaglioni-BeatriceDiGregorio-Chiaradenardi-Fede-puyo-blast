package it.unibo.view;

import javax.swing.*;

import it.unibo.model.Scale;

import it.unibo.view.interfaces.CannonViewInterface;

import java.awt.*;
import java.net.URL;

public class CannonView extends JPanel implements CannonViewInterface {

    private Image cannonImage;
    private int cannonWidth;
    private int cannonHeight;
    private int cannonX;
    private int cannonY;
    private int Angle;
    private Scale scale;

    // Immagini per i diversi angoli
    private final String[] angleImages = {
            "CannonImage.png", // per angoli da 0 a 10
            "CannonImage1.png", // per angoli da 11 a 20
            "CannonImage2.png", // per angoli da 21 a 30
            "CannonImage3.png", // per angoli da 31 a 40
            "CannonImage4.png" // per angoli da 41 in poi
    };

    public CannonView(final String imagePath, Scale scale) {
        this.scale = scale;
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
        this.Angle = 0;
    }

    public final void updateImage() {
        String imagePath = "";

        if (this.Angle <= 12) {
            imagePath = angleImages[0];
        } else if (this.Angle >= 13 && this.Angle <= 24) {
            imagePath = angleImages[1];
        } else if (this.Angle >= 25 && this.Angle <= 36) {
            imagePath = angleImages[2];
        } else if (this.Angle >= 37 && this.Angle <= 48) {
            imagePath = angleImages[3];
        } else if (this.Angle >= 49) {
            imagePath = angleImages[4];
        }

        // Carica l'immagine corrispondente all'angolo
        final URL imageUrl = getClass().getClassLoader().getResource("images/" + imagePath);

        if (imageUrl == null) {
            System.err.println("Immagine non trovata: " + imagePath);
            return;
        }

        this.cannonImage = new ImageIcon(imageUrl).getImage();
        this.cannonHeight = cannonImage.getWidth(null); // Ricalcola la larghezza e l'altezza
        this.cannonWidth = cannonImage.getHeight(null);
    }

    @Override
    final public void draw(final Graphics g) {
        g.drawImage(cannonImage, cannonX, cannonY, null);
    }

    @Override
    final public void setCannonPosition(final int newX, final int newY) {
        this.cannonX = newX;
        this.cannonY = newY;
    }

    @Override
    final public void setCannonAngle(final int newAngle) {
        this.Angle = newAngle;
        updateImage();
    }

    // Metodo per ottenere la larghezza del cannone
    final public int getCannonWidth() {
        return this.cannonWidth;
    }

    final public int getCannonHeight() {
        return this.cannonHeight;
    }

}
