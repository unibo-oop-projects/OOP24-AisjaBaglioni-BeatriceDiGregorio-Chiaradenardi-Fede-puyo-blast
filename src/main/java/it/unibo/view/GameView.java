//include in rendering dello sfondo per avviare il gioco


//Disegna la griglia (classe grid), i Puyo(clase puyo) eto il cannone(classe cannone) 
//classe con il pannello di gioco avviato
package it.unibo.view;

import it.unibo.view.interfaces.GameViewInterface;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameViewInterface {
    private BackGround background;
    private final PuyoRenderer renderer;
    // private final Grid grid; 

    public GameView() {
        background = new BackGround("Scan10008.JPG");
        this.renderer = new PuyoRenderer(); 
        //this.grid = grid;  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());

        /*
         * 
         * //disegna i Puyo dalla griglia
        List<Puyo> puyos = grid.getPuyos();
        for (Puyo puyo : puyos) {
            renderer.render(g, puyo);
        }
         */
    }

    /**
     * public void updateGame() {
        // Aggiorna la griglia, ad esempio facendo cadere i Puyo
        for (Puyo puyo : grid.getPuyos()) {
            if (puyo.isFalling() && !puyo.checkCollision(grid.getGrid())) {
                puyo.moveDown();
            }
        }
        repaint(); // Richiama paintComponent per aggiornare la grafica
    }
     */

    @Override
    public void render(Graphics g, int width, int height) {
        background.draw(g, width, height);
    }

    @Override
    public void startGame() {
        System.out.println("Game started! ciao belli");
        // Logica aggiuntiva per avviare il gioco

        /**
         * // Logica per avviare il gioco
        Timer timer = new Timer(500, e -> updateGame());
        timer.start();
         */
    }
}
