package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import it.unibo.controller.TryAgainController;
import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ClickInterface;

public class TryAgainView implements ClickInterface {
    private Image tryAgainImage;
    private Scale scale;
    private int imageWidth;
    private int imageHeight;
    private TryAgainController controller; // Riferimento al controller

    public TryAgainView(Scale scale, TryAgainController controller) {
        this.scale = scale;
        this.controller = controller;  // Assegna il controller

        // Carica l'immagine del pulsante
        URL imagePath = getClass().getClassLoader().getResource("images/tryagain_button.png");
        if (imagePath == null) {
            System.out.println("Errore: Immagine 'tryagain_button.png' non trovata.");
        } else {
            tryAgainImage = new ImageIcon(imagePath).getImage();
            this.imageWidth = tryAgainImage.getWidth(null);
            this.imageHeight = tryAgainImage.getHeight(null);
        }
    }

    public void draw(Graphics g) {
        Rectangle buttonArea = getArea();
        g.drawImage(
            tryAgainImage,
            buttonArea.upleft.x(),
            buttonArea.upleft.y(),
            buttonArea.lowright.x(),
            buttonArea.lowright.y(),
            0,
            0,
            imageWidth,
            imageHeight,
            null);
    }

    public Rectangle getArea() {
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int x = 10;
        int y = this.scale.getScale() - newHeight - 50;
        Point2DI upleft = new Point2DI(x, y);
        Point2DI lowright = new Point2DI(x + newWidth, y + newHeight);
        return new Rectangle(upleft, lowright);
    }

    @Override
    public boolean isClicked(Point2DI pos) {
        return getArea().isInside(pos);
    }

    @Override
    public void doAction() {
        if (controller != null) {
            controller.handleClick();  // Chiama il metodo del controller per gestire l'azione
        } else {
            System.out.println("Errore: controller non inizializzato.");
        }
    }
}
