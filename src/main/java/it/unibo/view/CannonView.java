package it.unibo.view;

import javax.swing.*;

import it.unibo.model.Scale;
import it.unibo.view.interfaces.CannonViewInterface;
import it.unibo.model.CannonModel;

import java.awt.*;
import java.net.URL;

public class CannonView extends JPanel implements CannonViewInterface {

    private Image[] cannonImages;
    private CannonModel cannonModel;
    private int imageWidth;
    private int imageHeight;
    private Scale scale;  

    public CannonView(Scale scale, CannonModel cannonModel) {
        this.scale = scale;
        this.cannonModel = cannonModel;
        this.cannonImages = new Image[5];
        String[] cannonImagePaths = {"CannonImage.png", "CannonImage1.png", "CannonImage2.png", "CannonImage3.png", "CannonImage4.png"};

        for (int i = 0; i < cannonImagePaths.length; i++) {
            final URL imageUrl = getClass().getClassLoader().getResource("images/" + cannonImagePaths[i]);
             if (imageUrl == null) {
                System.err.println("Immagine non trovata: " + cannonImagePaths[i]);
            } else {
                this.cannonImages[i] = new ImageIcon(imageUrl).getImage();
            }
        }
        // Inizializza dimensioni e posizione
        if (this.cannonImages[0] != null) {
            this.imageWidth = cannonImages[0].getHeight(null);
            this.imageHeight = cannonImages[0].getWidth(null);
        }
    }

    @Override
    public final void draw(final Graphics g) {
        //La larghezza del cannone occupa tot della larghezza della finestra
        int newWidth = this.scale.getScale() / 10;
        //L'altezza è calcolata in proporzione
        // newWidth : newHeight = imageWidth : imageHeight
        int newHeight = (newWidth * this.imageHeight)/this.imageWidth;
        double angle = this.cannonModel.getAngle();
        int y = (this.scale.getScale() * 9)/10;
        // xModel : 1 = x : scale - newWidth
        double xdouble = this.cannonModel.getX() * (this.scale.getScale() - newWidth);
        int x = (int)xdouble;
        // Calcola l'indice dell'immagine in base all'angolo
        int imageIndex = getImageIndexForAngle(angle);

        // Disegna l'immagine corrispondente
        // targetx1, targety1, targetx2, targety2, sourcex1, sourcey1, sourcex2, sourcey2
        g.drawImage(cannonImages[imageIndex], x, y, x + newWidth, y + newHeight, 0, imageWidth, 0, imageHeight, null);
    }

    public int getImageIndexForAngle(final double angle) {
        if (angle <= 12) {
            return 0;
        } else if (angle <= 24) {
            return 1;
        } else if (angle <= 36) {
            return 2;
        } else if (angle <= 48) {
            return 3;
        } else {
            return 4;
        }
    }
}
