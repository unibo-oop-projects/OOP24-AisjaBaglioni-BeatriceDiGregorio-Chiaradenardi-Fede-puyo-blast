//include in rendering dello sfondo per avviare il gioco


//Disegna la griglia (classe grid), i Puyo(clase puyo) eto il cannone(classe cannone) 
//classe con il pannello di gioco avviato
package it.unibo.view;

import it.unibo.model.Grid;
import it.unibo.model.Scale;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.interfaces.GameViewInterface;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameView extends JPanel implements GameViewInterface, KeyListener {
    private BackGround background;
    private final PuyoRenderer renderer;
    private final Grid grid; 
    private final CannonView cannonView;
    private final CannonSightView cannonSightView;
    private final ProgressBarView progressBarView;
    private Scale scale;
    private PauseView pauseView;

    //pausa
    private boolean isPaused; //stato di pausa
    private Timer gameTimer; //timer per aggiornare il gioco


    //questo metodo fede
    public GameView(Grid grid, Scale scale) {
        this.scale = scale;
        this.background = new BackGround("background.jpg");
        this.renderer = new PuyoRenderer(this.scale); 
        this.cannonView = new CannonView("CannonImage.png", scale);
        this.cannonSightView = new CannonSightView("CannonSightView.png");
        this.progressBarView = new ProgressBarView("ProgressBarEmpty.png", "ProgressBarFull.png");
        this.grid = grid;
        this.pauseView = new PauseView(this);
        this.isPaused = false;

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        gameTimer = new Timer(500, e -> updateGame());
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
                    renderer.render(g, grid, y, x);
                    cannonSightView.draw(g);
                }
            }
        }

        //disegna il messaggio di pausa
        if (isPaused) {
            drawPauseMessage(g);
        }
    }

    //fede
    private void drawPauseMessage(Graphics g) {
        String pauseText = "Game Paused";
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.RED);
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(pauseText)) / 2;
        int y = getHeight() / 2;
        g.drawString(pauseText, x, y);
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
        //Timer timer = new Timer(500, e -> updateGame());
        gameTimer.start();
    }

    //fede
    // Metodi per mettere in pausa e riprendere il gioco
    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            gameTimer.stop(); // Ferma il timer
        } else {
            gameTimer.start(); // Riprendi il timer
        }
        repaint(); // Aggiorna la grafica per mostrare il messaggio di pausa
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilizzato
    }

    //fede
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            togglePause(); // Alterna lo stato di pausa
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilizzato
    }


    @Override
    public boolean isPaused() {

        return isPaused;
    }
}
