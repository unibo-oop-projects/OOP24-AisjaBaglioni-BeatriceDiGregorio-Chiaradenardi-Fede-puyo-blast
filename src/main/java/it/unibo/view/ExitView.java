package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.model.Scale;


public class ExitView {
    private Image exit;
    Scale scale;
    private int imageWidth;
    private int imageHeight;

    public ExitView(Scale scale) {
        this.scale = scale;
        URL exit_path = getClass().getClassLoader().getResource("images/mainmenu_button.png");
        exit = new ImageIcon(exit_path).getImage();
        this.imageWidth = exit.getWidth(null);
        this.imageHeight = exit.getHeight(null);   
    }

    final public void draw(Graphics g) {
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int x = this.scale.getScale() / 28;
        int y = 0;
        g.drawImage(
            exit,
            x,
            y,
            x + newWidth,
            y + newHeight,
            0,
            0,
            imageWidth,
            imageHeight,
            null);
    }
}
