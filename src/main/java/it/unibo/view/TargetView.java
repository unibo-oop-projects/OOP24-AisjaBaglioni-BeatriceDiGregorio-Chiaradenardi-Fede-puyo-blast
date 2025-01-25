package it.unibo.view;

import javax.swing.*;

import it.unibo.view.interfaces.TargetViewInterface;

import java.awt.*;
import java.net.URL;

public class TargetView extends JPanel implements TargetViewInterface {
    
    private Image cannonSightImage;
    private int cannonSightX;
    private int cannonSightY;

    public TargetView(final String imagePath){
        final URL imageUrl = getClass().getClassLoader().getResource("images/"+ imagePath);

        if (imageUrl == null){
            System.err.println("Immagine non trovata: " + imagePath);
            return;
        }

        this.cannonSightImage = new ImageIcon(imageUrl).getImage();
        this.cannonSightX = 0;
        this.cannonSightY = 0;
    }

    @Override
    final public void draw(final Graphics g){
        g.drawImage(cannonSightImage, cannonSightX, cannonSightY, null);
    }

    @Override
    final public void setCannonSightPosition(final int newX, final int newY){
        this.cannonSightX = newX;
        this.cannonSightY = newY;
    }
}
