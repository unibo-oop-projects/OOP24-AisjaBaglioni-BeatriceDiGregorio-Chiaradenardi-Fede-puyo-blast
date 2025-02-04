//AISJA

package it.unibo;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import it.unibo.controller.GameManager;
import it.unibo.controller.MenuManager;
import it.unibo.model.Scale;

public class Main extends JFrame implements GameListener {
    private final Scale scale;
    private MenuManager menuManager;
    private GameManager gameManager;

    public Main(String title) {
        super(title);
        // Sets window size to dynamic screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int dim = (int) (Math.min(width, height) * 0.75);
        this.scale = new Scale(dim);
        this.setSize(scale.getScale(), scale.getScale());
        // Not resizable
        this.setResizable(false);
        // Exits on close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Creates the menu: parameters are the event listener and the frame
        this.menuManager = new MenuManager(this, scale);
        this.add(menuManager);
        this.menuManager.setVisible(true);
    }

    private void resetManager() {
        if (this.gameManager != null) {
            this.gameManager.setVisible(false);
            this.remove(gameManager);
            this.gameManager = null;
        }
        if (this.menuManager != null) {
            this.menuManager.setVisible(false);
            this.remove(menuManager);
            this.menuManager = null;
        }
    }

    @Override
    public void startGame(GameEvent e) {
        System.out.println("startGame");
        this.resetManager();
        this.gameManager = new GameManager(this, scale, e.levelConfig);
        this.add(this.gameManager);
        this.gameManager.setVisible(true);
    }

    @Override
    public void goToMainMenu(GameEvent e) {
        System.out.println("goToMainMenu");
        this.resetManager();
        this.menuManager = new MenuManager(this, scale);
        this.add(this.menuManager);
        this.menuManager.setVisible(true);
    }

    public static void main(String[] args) {
        // Adds in the system process queue this application
        EventQueue.invokeLater(() -> {
            Main main = new Main("Puyo Pop: Blast!");
            main.setVisible(true);
        });
    }
}