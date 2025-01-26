//AISJA

package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.model.PauseModel;
import it.unibo.model.Scale;

public class PauseView {
    private Image[] pause;
    private PauseModel model;
    Scale scale;
    private int imageWidth;
    private int imageHeight;

    public PauseView(Scale scale, PauseModel model) {
        this.scale = scale;
        this.pause = new Image[2];
        URL pause_path = getClass().getClassLoader().getResource("images/pause_button.png");
        URL resume_path = getClass().getClassLoader().getResource("images/resume_button.png");
        this.pause[0] = new ImageIcon(pause_path).getImage();
        this.pause[1] = new ImageIcon(resume_path).getImage();
        this.imageWidth = this.pause[0].getWidth(null);
        this.imageHeight = this.pause[0].getHeight(null);
        this.model = model;
        // PauseController pause_ctrl = new PauseController(this.model);
    }

    final public void draw(Graphics g) {
        // int cellsize = this.scale.getScale()/16;
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int x = this.scale.getScale() - newWidth - this.scale.getScale() / 28;
        int y = 0;
        if (!this.model.getPause()) {
            g.drawImage(
                    this.pause[0],
                    x,
                    y,
                    x + newWidth,
                    y + newHeight,
                    0,
                    0,
                    imageWidth,
                    imageHeight,
                    null);
        } else {
            g.drawImage(
                    this.pause[1],
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
}