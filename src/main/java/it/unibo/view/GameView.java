//include in rendering dello sfondo per avviare il gioco


//Disegna la griglia (classe grid), i Puyo(clase puyo) eto il cannone(classe cannone) 
//classe con il pannello di gioco avviato
package it.unibo.view;

import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.interfaces.GameViewInterface;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameViewInterface {
    private BackGround background;
    private final PuyoRenderer renderer;
    private final Grid grid; 

    //questo metodo fede
    public GameView(Grid grid) {
        this.background = new BackGround("Scan10008.JPG");
        this.renderer = new PuyoRenderer(); 
        this.grid = grid;  
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());

        //disegna i Puyo dalla griglia
        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                PuyoInterface puyo = grid.getPuyo(x, y);
                if (puyo != null) {
                    renderer.render(g, (Puyo) puyo);
                }
            }
        }
    }

    //chiara
    public void updateGame() {
        grid.updateGrid(); //logica di caduta dei Puyo
        repaint(); //aggiorna la grafica
    }
    

    @Override
    public void render(Graphics g, int width, int height) {
        background.draw(g, width, height);
    }

    @Override
    public void startGame() {
        System.out.println("Game started! ciao belli");

        //logica per avviare il gioco
        Timer timer = new Timer(500, e -> updateGame());
        timer.start();
    }
}
