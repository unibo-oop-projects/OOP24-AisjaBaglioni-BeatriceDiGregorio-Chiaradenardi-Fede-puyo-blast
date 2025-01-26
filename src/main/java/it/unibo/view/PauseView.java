//AISJA

package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.controller.PauseController;
import it.unibo.model.PauseModel;
import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ClickInterface;

public class PauseView implements ClickInterface {
    private Image[] pause;
    private PauseModel model;
    Scale scale;
    private int imageWidth;
    private int imageHeight;
    private PauseController controller;

    public PauseView(Scale scale, PauseModel model, PauseController controller) {
        this.scale = scale;
        this.pause = new Image[2];
        URL pause_path = getClass().getClassLoader().getResource("images/pause_button.png");
        URL resume_path = getClass().getClassLoader().getResource("images/resume_button.png");
        this.pause[0] = new ImageIcon(pause_path).getImage();
        this.pause[1] = new ImageIcon(resume_path).getImage();
        this.imageWidth = this.pause[0].getWidth(null);
        this.imageHeight = this.pause[0].getHeight(null);
        this.model = model;
        this.controller = controller;
    }

    final public void draw(Graphics g) {
        Rectangle button = getArea();
        if (!this.model.getPause()) {
            g.drawImage(
                    this.pause[0],
                    button.upleft.x(),
                    button.upleft.y(),
                    button.lowright.x(),
                    button.lowright.y(),
                    0,
                    0,
                    imageWidth,
                    imageHeight,
                    null);
        } else {
            g.drawImage(
                    this.pause[1],
                    button.upleft.x(),
                    button.upleft.y(),
                    button.lowright.x(),
                    button.lowright.y(),
                    0,
                    0,
                    imageWidth,
                    imageHeight,
                    null);
        }
    }

    @Override
    public boolean isClicked(Point2DI pos) {
        Rectangle button = getArea();
        return button.isInside(pos);
    }

    @Override
    public void doAction() {
        this.controller.setPause();
        System.out.println("Hai cliccato Pause/Resume");
    }

    public Rectangle getArea(){
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int x = this.scale.getScale() - newWidth - this.scale.getScale() / 28;
        int y = 0;
        Point2DI upleft = new Point2DI(x, y);
        Point2DI lowright = new Point2DI(x + newWidth, y + newHeight);
        return new Rectangle(upleft, lowright);
    }
    
}