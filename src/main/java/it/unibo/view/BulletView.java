//AISJA

package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.model.BulletModel;
import it.unibo.model.Point2D;
import it.unibo.model.Scale;

public class BulletView {
    private BulletModel model;
    private Image sprites;
    Scale scale;
    /*
     * // Sono uguali a quelle del cannone
     * private int spawnX;
     * private int spawnY;
     * // Sono uguali a quelle del mirino
     * private int targetX;
     * private int targetY;
     */

    public BulletView(BulletModel model, Scale scale) {
        this.scale = scale;
        this.model = model;
        URL image_path = getClass().getClassLoader().getResource("images/puyosprites.png");
        this.sprites = new ImageIcon(image_path).getImage();
    }

    public void draw(Graphics g) {
        if (!this.model.isActive()) {
            return;
        }
        int cellsize = this.scale.getScale() / 16;
        Point2D curPos = this.model.getCurrentPosition();
        int x = (int) curPos.x();
        int y = (int) curPos.y();
        g.drawImage(
                sprites,
                x - cellsize / 2,
                y - cellsize / 2,
                x + cellsize / 2,
                y + cellsize / 2,
                18 * 32, 3 * 32,
                19 * 32, 4 * 32,
                null);
    }
}