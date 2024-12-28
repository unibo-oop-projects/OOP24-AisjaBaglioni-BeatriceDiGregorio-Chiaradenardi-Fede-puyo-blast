//include in rendering dello sfondo per avviare il gioco


//Disegna la griglia (classe grid), i Puyo(clase puyo) eto il cannone(classe cannone) 
//classe con il pannello di gioco avviato
package it.unibo.view;

import it.unibo.view.interfaces.GameViewInterface;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameViewInterface {
    private BackGround background;

    public GameView() {
        background = new BackGround("Scan10008.JPG");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g, int width, int height) {
        background.draw(g, width, height);
    }

    @Override
    public void startGame() {
        System.out.println("Game started! ciao belli");
        // Logica aggiuntiva per avviare il gioco
    }
}
