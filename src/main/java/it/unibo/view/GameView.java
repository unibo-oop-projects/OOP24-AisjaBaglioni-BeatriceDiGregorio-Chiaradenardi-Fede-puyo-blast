//include in rendering dello sfondo per avviare il gioco

package it.unibo.view;

import javax.swing.*;
import java.awt.*;

    

public class GameView extends JPanel {
    private BackGround background;

    public GameView() {
        background = new BackGround("percorso");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());
    }

    public void startGame() {
        // Logica per avviare il gioco
        System.out.println("Game started!");
    }
}
    





