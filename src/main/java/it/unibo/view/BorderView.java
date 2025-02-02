package it.unibo.view;

import it.unibo.model.Scale;

import java.awt.*;
import java.net.URL;

import javax.swing.ImageIcon;

public class BorderView {
    private Image borderImage;
    private Scale scale;
    private int imageWidth;
    private int imageHeight;

    BorderView(Scale scale) {
        this.scale = scale;
        final URL imageURL = getClass().getClassLoader().getResource("images/" + "gridborder.png");
        this.borderImage = new ImageIcon(imageURL).getImage();
        this.imageHeight = borderImage.getHeight(null);
        this.imageWidth = borderImage.getWidth(null);
    }

    public void draw(final Graphics g) {
        int cellsize = this.scale.getScale() / 16;
        int shrink = cellsize / 2;
        /*
         * int newWidth = this.scale.getScale() / 10;
         * int x = cellsize * 8;
         * int y = cellsize * 5;
         */
        g.drawImage(
                borderImage,
                cellsize * 3 + shrink,
                0 + shrink,
                cellsize * 13 - shrink,
                cellsize * 10 - shrink,
                0, 0,
                imageWidth, imageHeight, null);
    }
}
