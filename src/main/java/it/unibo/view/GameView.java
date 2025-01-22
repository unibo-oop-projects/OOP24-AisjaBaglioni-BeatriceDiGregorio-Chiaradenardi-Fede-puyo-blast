//include in rendering dello sfondo per avviare il gioco


//Disegna la griglia (classe grid), i Puyo(clase puyo) eto il cannone(classe cannone) 
//classe con il pannello di gioco avviato
package it.unibo.view;

import it.unibo.model.Grid;
import it.unibo.model.PauseModel;
import it.unibo.model.Puyo;
import it.unibo.model.Scale;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.interfaces.GameViewInterface;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameViewInterface {
    private BackGround background;
    private final PuyoRenderer renderer;
    private final Grid grid; 
    private final CannonView cannonView;
    private final CannonSightView cannonSightView;
    private final ProgressBarView progressBarView;
    private Scale scale;
    private PauseView pauseView;
    private PauseModel pauseModel;

    //questo metodo fede
    public GameView(Grid grid, Scale scale) {
        this.scale = scale;
        this.background = new BackGround("background.jpg");
        this.renderer = new PuyoRenderer(this.scale); 
        this.cannonView = new CannonView("CannonImage.png", scale);
        this.cannonSightView = new CannonSightView("CannonSightView.png");
        this.progressBarView = new ProgressBarView("ProgressBarEmpty.png", "ProgressBarFull.png");
        this.grid = grid;
        this.pauseView = new PauseView(this.pauseModel, this);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());
        cannonView.draw(g);
        progressBarView.paintComponent(g);
        pauseView.draw();

        //disegna i Puyo dalla griglia
        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                PuyoInterface puyo = grid.getPuyo(x, y);
                if (puyo != null) {
                    renderer.render(g, (Puyo) puyo);
                    cannonSightView.draw(g);
                }
            }
        }
    }

    //chiara
    public void updateGame() {
        grid.updateGrid(); //logica di caduta dei Puyo
        repaint(); //aggiorna la grafica
    }

    public CannonView getCannonView(){
        return this.cannonView;
    }

    public CannonSightView getCannonSightView(){
        return this.cannonSightView;
    }

    public ProgressBarView getProgressBarView() {
        return this.progressBarView;
    }
    

    @Override
    public void render(Graphics g, int width, int height) {
        background.draw(g, width, height);
        // cannonView.draw(g, width, height);
    }

    @Override
    public void startGame() {
        System.out.println("Game started! ciao belli");

        //logica per avviare il gioco
        Timer timer = new Timer(500, e -> updateGame());
        timer.start();
    }
}
