package it.unibo.view;

import javax.swing.*;

import it.unibo.model.Scale;
import it.unibo.view.interfaces.CannonViewInterface;
import it.unibo.view.interfaces.ViewInterface;
import it.unibo.model.CannonModel;
import it.unibo.model.Point2DI;

import java.awt.*;
import java.net.URL;

public class CannonView extends JPanel implements CannonViewInterface, ViewInterface {

    private Image[] cannonImages;
    private CannonModel cannonModel;
    private int imageWidth;
    private int imageHeight;
    private Scale scale;

    public CannonView(Scale scale, CannonModel cannonModel) {
        this.scale = scale;
        this.cannonModel = cannonModel;
        this.cannonImages = new Image[5];
        String[] cannonImagePaths = { "CannonImage.png", "CannonImage1.png", "CannonImage2.png", "CannonImage3.png",
                "CannonImage4.png" };

        for (int i = 0; i < cannonImagePaths.length; i++) {
            final URL imageUrl = getClass().getClassLoader().getResource("images/" + cannonImagePaths[i]);
            if (imageUrl == null) {
                System.err.println("Immagine non trovata: " + cannonImagePaths[i]);
            } else {
                this.cannonImages[i] = new ImageIcon(imageUrl).getImage();
            }
        }
        // Inizializza dimensioni e posizione
        this.imageWidth = cannonImages[0].getWidth(null);
        this.imageHeight = cannonImages[0].getHeight(null);
    }

    public CannonModel getCannonModel() {
        return this.cannonModel;
    }

    public Point2DI getCenter() {
        // La larghezza del cannone occupa tot della larghezza della finestra
        int newWidth = this.scale.getScale() / 10;
        int puyoCellSize = this.scale.getScale() / 16;
        int offsetX = puyoCellSize * 4;
        // L'altezza Ã¨ calcolata in proporzione
        // newWidth : newHeight = imageWidth : imageHeight
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int y = (this.scale.getScale() * 6) / 8;
        // xModel : 1 = x : scale - newWidth
        double xdouble = offsetX + this.cannonModel.getX() * (this.scale.getScale() - newWidth - 2 * offsetX);
        int x = (int) xdouble;
        return new Point2DI(x + newWidth / 2, y + newHeight / 2);
    }

    @Override
    public final void draw(final Graphics g) {
        int newWidth = this.scale.getScale() / 10;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        Point2DI center = getCenter();
        // Calcola l'indice dell'immagine in base all'angolo
        double angle = this.cannonModel.getAngle();
        int imageIndex = getImageIndexForAngle(angle);

        // Disegna l'immagine corrispondente
        // targetx1, targety1, targetx2, targety2, sourcex1, sourcey1, sourcex2,
        // sourcey2
        g.drawImage(
                cannonImages[imageIndex],
                center.x() - newWidth / 2,
                center.y() - newHeight / 2,
                center.x() + newWidth / 2,
                center.y() + newHeight / 2,
                0, 0,
                imageWidth, imageHeight, null);
    }

    public int getImageIndexForAngle(final double angle) {
        if (angle <= 0.2) {
            return 0;
        } else if (angle <= 0.4) {
            return 1;
        } else if (angle <= 0.6) {
            return 2;
        } else if (angle <= 0.8) {
            return 3;
        } else {
            return 4;
        }
    }
}