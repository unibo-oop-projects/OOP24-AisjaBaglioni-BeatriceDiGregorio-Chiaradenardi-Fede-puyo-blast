//AISJA

package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.model.BulletModel;
import it.unibo.model.Scale;

public class BulletView {
    private BulletModel model;
    private Image sprites;
    Scale scale;
    /* // Sono uguali a quelle del cannone
    private int spawnX;
    private int spawnY;
    // Sono uguali a quelle del mirino
    private int targetX;
    private int targetY;*/

    public BulletView(BulletModel model, final String imagePath, Scale scale) {
        this.scale = scale;
        this.model = model;
        URL image_path = getClass().getClassLoader().getResource("images/puyosprites.png");
        this.sprites = new ImageIcon(image_path).getImage();
    }

    public void draw(Graphics g) {
        int cellsize = this.scale.getScale() / 16;
        int x = model.getX();
        int y = model.getY();
        g.drawImage(sprites, x, y, x + cellsize, y + cellsize, 18*32, 3*32, 19*32, 5*32, null);
    }
}
