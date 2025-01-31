package it.unibo.view;

import it.unibo.controller.ClickController;
import it.unibo.controller.ScreenManager;
import it.unibo.model.*;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.interfaces.GameViewInterface;
import it.unibo.view.interfaces.ClickInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    private final TryAgainView tryAgainView;
    private final ScoreView scoreView;
    private Scale scale;
    private Set<ClickInterface> clickables;
    private boolean isPaused;

    public GameView(Grid grid, Scale scale, PuyoRenderer puyoRenderer, CannonModel cannonModel, CannonView cannonView,
                    ProgressBarModel progressModel, BulletModel bulletModel, 
                    PauseView pauseView, TryAgainView tryAgainView, 
                    ExitView exitView,ScoreView scoreView, ScreenManager screenManager) {

        this.scale = scale;
        this.pauseView = pauseView;
        this.exitView = exitView;
        this.tryAgainView = tryAgainView;
        this.clickables = new HashSet<>();
        this.clickables.add(this.exitView);
        this.clickables.add(this.tryAgainView);
        this.clickables.add(this.pauseView);
        this.clickController = new ClickController(clickables);
        this.background = new BackGround("background.jpg");
        this.renderer = puyoRenderer;
        this.cannonView = cannonView;
        this.cannonSightView = new TargetView("CannonSightView.png", this.scale, cannonModel);
        this.progressBarView = new ProgressBarView(progressModel, this.scale);
        this.bulletView = new BulletView(bulletModel, this.scale);
        this.borderView = new BorderView(this.scale);
        this.scoreView = scoreView;
        this.grid = grid;
        this.isPaused = false;

        this.addMouseListener(clickController);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());

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
        bulletView.draw(g);
        cannonView.draw(g);
        progressBarView.paintComponent(g);

        exitView.draw(g);
        tryAgainView.draw(g);
        scoreView.draw(g);
        pauseView.draw(g);

        if (isPaused) {
            drawPauseMessage(g);
        }
    }

    private void drawPauseMessage(Graphics g) {
        String pauseText = "Game Paused";
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.RED);
        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(pauseText)) / 2;
        int y = getHeight() / 2;
        g.drawString(pauseText, x, y);
    }

    public void updateGame() {
        if (!isPaused) {
            grid.updateGrid();
            repaint();
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
    }

    @Override
    public void startGame() {
        System.out.println("Game started!");
    }

    public void togglePause() {
        System.out.println("Toggling pause: " + isPaused);
        isPaused = !isPaused;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public boolean isPaused() {
        return isPaused;
    }
}