//include in rendering dello sfondo per avviare il gioco

//Disegna la griglia (classe grid), i Puyo(clase puyo) eto il cannone(classe cannone) 
//classe con il pannello di gioco avviato
package it.unibo.view;

import it.unibo.controller.ClickController;
import it.unibo.model.BulletModel;
import it.unibo.model.CannonModel;
import it.unibo.model.Grid;
import it.unibo.model.ProgressBarModel;
import it.unibo.model.Scale;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.interfaces.GameViewInterface;
import it.unibo.view.interfaces.ClickInterface;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameView extends JPanel implements GameViewInterface, KeyListener {
    private ClickController clickController;
    private BackGround background;
    private final PuyoRenderer renderer;
    private final Grid grid;
    private final CannonView cannonView;
    private final TargetView cannonSightView;
    private final ProgressBarView progressBarView;
    private final BulletView bulletView;
    private final BorderView borderView;
    private final PauseView pauseView;
    private final ExitView exitView;
    private final TryAgainView tryAgain;
    private Scale scale;
    private Set<ClickInterface> clickables; 
    // private final PauseView pauseView;
    // pausa
    private boolean isPaused; // stato di pausa
    // private Timer gameTimer; // timer per aggiornare il gioco

    // questo metodo fede
    public GameView(Grid grid, Scale scale, CannonModel cannonModel, CannonView cannonView,
            ProgressBarModel progressModel,
            BulletModel bulletModel, PauseView pauseView, ExitView exitView, TryAgainView tryAgain) {
        this.scale = scale;
        this.pauseView = pauseView;
        this.exitView = exitView;
        this.tryAgain = tryAgain;
        this.clickables = new HashSet<>();
        this.clickables.add(this.exitView);
        this.clickables.add(this.tryAgain);
        this.clickables.add(this.pauseView);
        this.clickController = new ClickController(clickables);
        this.background = new BackGround("background.jpg");
        this.renderer = new PuyoRenderer(this.scale);
        this.cannonView = cannonView;
        this.cannonSightView = new TargetView("CannonSightView.png", this.scale, cannonModel);
        this.progressBarView = new ProgressBarView(progressModel, this.scale);
        this.bulletView = new BulletView(bulletModel, this.scale);
        this.borderView = new BorderView(this.scale);
        this.grid = grid;
        // this.pauseView = new PauseView(this);
        this.isPaused = false;

        this.addMouseListener(clickController);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        // gameTimer = new Timer(500, e -> updateGame());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());

        // Disegna i Puyo dalla griglia
        if (!isPaused) {
            for (int y = 0; y < grid.getRows(); y++) {
                for (int x = 0; x < grid.getCols(); x++) {
                    PuyoInterface puyo = grid.getPuyo(x, y);
                    if (puyo != null) {
                        renderer.render(g, grid, y, x);
                    }
                }
            }
        }

        borderView.draw(g);

        cannonSightView.draw(g);

        this.bulletView.draw(g);

        cannonView.draw(g);
        progressBarView.paintComponent(g);

        exitView.draw(g);
        tryAgain.draw(g);
        pauseView.draw(g);
        // Disegna il messaggio di pausa
        if (isPaused) {
            drawPauseMessage(g);
        }
    }

    // fede
    private void drawPauseMessage(Graphics g) {
        String pauseText = "Game Paused";
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.RED);
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(pauseText)) / 2;
        int y = getHeight() / 2;
        g.drawString(pauseText, x, y);
    }

    // chiara
    public void updateGame() {
        if (!isPaused) {
            grid.updateGrid(); // logica di caduta dei Puyo
            repaint(); // aggiorna la grafica
        }

    }

    public CannonView getCannonView() {
        return this.cannonView;
    }

    public TargetView getCannonSightView() {
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

        // logica per avviare il gioco
        // Timer timer = new Timer(500, e -> updateGame());
        // gameTimer.start();
    }

    // fede
    // Metodi per mettere in pausa e riprendere il gioco
    public void togglePause() {
        System.out.println("Toggling pause: " + isPaused);
        isPaused = !isPaused;
        if (isPaused) {
            // gameTimer.stop();
            System.out.println("Game Timer stopped");
        } else {
            // gameTimer.start();
            System.out.println("Game Timer started");
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilizzato
    }

    // fede
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