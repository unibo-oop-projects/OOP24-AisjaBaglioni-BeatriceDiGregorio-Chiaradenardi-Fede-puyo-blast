package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.controller.ExitController;
import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ClickInterface;

public class ExitView implements ClickInterface {
    private Image exit;
    Scale scale;
    private int imageWidth;
    private int imageHeight;
    private ExitController controller;

    public ExitView(Scale scale, ExitController controller) {
        this.scale = scale;
        this.controller = controller;
        System.out.println("ExitView controller inizializzato: " + (controller != null)); // Log
        URL exit_path = getClass().getClassLoader().getResource("images/mainmenu_button.png");
        exit = new ImageIcon(exit_path).getImage();
        this.imageWidth = exit.getWidth(null);
        this.imageHeight = exit.getHeight(null);
    }

    final public void draw(Graphics g) {
        Rectangle button = getArea();
        g.drawImage(
                exit,
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
        int y = this.scale.getScale() / 16;
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
        if (controller != null) {
            controller.onExitClicked(); // Delegare l'azione al controller
        } else {
            System.out.println("Errore: controller non inizializzato.");
        }
    }
}
