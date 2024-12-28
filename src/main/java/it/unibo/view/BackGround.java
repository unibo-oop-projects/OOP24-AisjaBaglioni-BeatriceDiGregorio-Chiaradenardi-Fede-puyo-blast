//carica e visualizza lo sfondo del gioco
package it.unibo.view;


import java.awt.*;
import javax.swing.*;

public class BackGround {
    private Image backgroundImage;

    public BackGround(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    public void draw(Graphics g, int width, int height) {
        g.drawImage(backgroundImage, 0, 0, width, height, null);
    }
}
