package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import it.unibo.controller.LevelManager;
import it.unibo.model.Point2DI;
import it.unibo.controller.ScreenManager;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.ClickInterface;

public class TryAgain implements ClickInterface {
    private Image tryAgain;
    Scale scale;
    private int imageWidth;
    private int imageHeight;
    private LevelManager levelManager;
    private ScreenManager screenManager;  // Aggiungi un riferimento a ScreenManager
    private int currentLevel;

    public TryAgain(Scale scale, LevelManager levelManager, ScreenManager screenManager) {
        this.scale = scale;
        this.levelManager = levelManager; // Salva il riferimento al LevelManager
        this.screenManager = screenManager; // Salva il riferimento a ScreenManager
    
        // Carica l'immagine
        URL exit_path = getClass().getClassLoader().getResource("images/tryagain2_button.png");
        if (exit_path == null) {
            System.out.println("Immagine non trovata.");
        } else {
            tryAgain = new ImageIcon(exit_path).getImage();
            this.imageWidth = tryAgain.getWidth(null);
            this.imageHeight = tryAgain.getHeight(null);
        }
    }

    final public void draw(Graphics g) {
        Rectangle button = getArea();
        
        // Verifica se l'immagine è stata caricata correttamente
        if (tryAgain != null) {
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
        } else {
            System.out.println("Immagine non disponibile per il disegno.");
        }
    }

    public Rectangle getArea() {
        int newWidth = this.scale.getScale() / 7;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        
        // Posizionamento in basso a sinistra
        int x = 10;  // Distanza dal bordo sinistro
        int y = this.scale.getScale() - newHeight - 50;  // Distanza dal bordo inferiore        
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
    
        if (levelManager != null) {
            levelManager.resetLevel(currentLevel); // Passa il livello corrente a resetLevel
        } else {
            System.out.println("Errore: levelManager non inizializzato.");
        }

        // Chiamata per ripristinare il gioco completamente
        if (screenManager != null) {
            screenManager.resetGame(); // Chiamata per resettare completamente il gioco
        } else {
            System.out.println("Errore: screenManager non inizializzato.");
        }
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
