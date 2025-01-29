package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ClickInterface;

public class TryAgain implements ClickInterface {
    private Image tryAgain;
    Scale scale;
    private int imageWidth;
    private int imageHeight;

    public TryAgain(Scale scale) {
        this.scale = scale;
        URL exit_path = getClass().getClassLoader().getResource("images/try_again_button.png");
        tryAgain = new ImageIcon(exit_path).getImage();
        this.imageWidth = tryAgain.getWidth(null);
        this.imageHeight = tryAgain.getHeight(null);
    }

    final public void draw(Graphics g) {
        Rectangle button = getArea();
        g.drawImage(
                tryAgain,
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

    public Rectangle getArea() {
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int x = this.scale.getScale() / 28;
        int y = this.scale.getScale()/16;
        Point2DI upleft = new Point2DI(x, y);
        Point2DI lowright = new Point2DI(x + newWidth, y + newHeight);
        return new Rectangle(upleft, lowright);
    }

    @Override
    public boolean isClicked(Point2DI pos) {
        Rectangle button = getArea();
        return button.isInside(pos);
    }

    @Override
    public void doAction() {
        System.out.println("Hai cliccato try again");
    }
}
