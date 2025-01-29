//chiara
package it.unibo.controller;

import it.unibo.controller.interfaces.ScreenManagerInterface;
import it.unibo.model.BulletModel;
import it.unibo.model.CannonModel;
import it.unibo.model.Grid;
import it.unibo.model.KeyboardModel;
import it.unibo.model.Menu;
import it.unibo.model.PauseModel;
import it.unibo.model.ProgressBarModel;
import it.unibo.model.Puyo;
import it.unibo.model.Scale;
import it.unibo.view.CannonView;
import it.unibo.view.ExitView;
import it.unibo.view.GameView;
import it.unibo.view.MenuRules;
import it.unibo.view.PauseView;
import it.unibo.view.TryAgain;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class ScreenManager implements ScreenManagerInterface {
    private final JFrame frame;
    private final Menu menuView;
    private final MenuRules rulesView;
    private final GameView gameView;
    private final CannonController cannon;
    private final CannonView cannonView;
    private final ProgressBarModel progressBarModel;
    private final ProgressBarController progressBar;
    private final GameLoop gameLoop;
    private final KeyboardModel keyboardModel;
    private final CannonModel cannonModel;
    private final BulletModel bulletModel;
    private final BulletController bulletController;
    private final PuyoExplosionController puyoExplosionController;
    private final PauseView pauseView;
    private final PauseModel pauseModel;
    private final PauseController pauseController;
    private final ExitView exitView;
    private final TryAgain tryAgain;
    private LevelManager levelManager;
    private Timer dropTimer; // timer per far cadere i Puyo
    private Grid grid;
    private PuyoDropper puyoDropper;
    private String currentLevel = "";
    private Scale scale;

    public ScreenManager(String[] levels) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int dim = (int)(Math.min(width, height) * 0.75);
        this.scale = new Scale(dim);
        this.frame = new JFrame("Puyo Pop");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(scale.getScale(), scale.getScale());
        this.frame.setResizable(false);

        grid = new Grid(8, 8);
        this.levelManager = new LevelManager();
        this.exitView = new ExitView(scale);
        this.tryAgain = new TryAgain(scale, levelManager);
        this.menuView = new Menu(levels);
        this.rulesView = new MenuRules();
        this.cannonModel = new CannonModel();
        this.progressBarModel = new ProgressBarModel();
        this.progressBar = new ProgressBarController(this.progressBarModel);
        this.bulletModel = new BulletModel();
        this.cannonView = new CannonView(this.scale, cannonModel);
        this.pauseModel = new PauseModel();
        this.pauseController = new PauseController(pauseModel);
        this.pauseView = new PauseView(this.scale, pauseModel, pauseController);
        this.gameView = new GameView(grid, scale, cannonModel, cannonView, progressBarModel, bulletModel, pauseView,
                exitView, tryAgain);
        this.gameLoop = new GameLoop(this.gameView, new HashSet<>());
        this.keyboardModel = new KeyboardModel();
        this.cannon = new CannonController(this.cannonModel, this.keyboardModel, this.progressBar);
        this.bulletController = new BulletController(bulletModel, grid, keyboardModel, progressBar, cannonView, scale);
        this.puyoExplosionController = new PuyoExplosionController(grid);
        this.gameLoop.addTickListener(this.cannon);
        this.gameLoop.addTickListener(this.progressBar);
        this.gameLoop.addTickListener(this.bulletController);
        this.gameLoop.addTickListener(this.puyoExplosionController);
        setupMenuListeners();
        setupRulesListeners();
        initializeGrid();
    }

    // metodo per inizializzare le due righe di Puyo
    private void initializeGrid() {
        fillBottomRows(grid);
    }

    private void fillBottomRows(Grid grid) {
        String[] initialColors = { "red", "blue", "green", "yellow", "purple", "cyan" };
        for (int y = grid.getRows() - 2; y < grid.getRows(); y++) { // Ultime due righe
            for (int x = 0; x < grid.getCols(); x++) {
                String randomColor = initialColors[new Random().nextInt(initialColors.length)];
                Puyo puyo = new Puyo(randomColor, x, y);
                grid.addPuyo(puyo, x, y);
            }
        }
    }

    private void startGameWithConfig(LevelManager.LevelConfig config) {
        frame.getContentPane().removeAll();

        frame.getContentPane().add(gameView, BorderLayout.CENTER);

        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();

        gameView.startGame();

        configureInputHandlers();

        this.puyoDropper = new PuyoDropper(grid, config);
        this.gameLoop.addTickListener(puyoDropper);

        // stop qualsiasi Timer precedente
        /*
         * if (dropTimer != null) {
         * dropTimer.stop();
         * }
         * 
         * dropTimer = new Timer(config.getDelay(), event -> {
         * puyoDropper.fillGridRandomly(config.getPuyoCount());
         * });
         * 
         * dropTimer.setInitialDelay(2000); // ritardo di 2 secondi prima di iniziare
         * dropTimer.start();
         */

        this.gameLoop.startGame();
    }

    private void configureInputHandlers() {
        KeyboardController keyboardController = new KeyboardController(this.keyboardModel);
        gameView.addKeyListener(keyboardController);
        gameView.setFocusable(true);
    }

    private void setupMenuListeners() {
        // Quando avvii un livello dal menu
        menuView.getStartButton().addActionListener(e -> {
            String selectedLevel = menuView.getSelectedLevel();
            if (!selectedLevel.equals(currentLevel)) {
                currentLevel = selectedLevel; // aggiorna il livello corrente
                LevelManager.LevelConfig config = levelManager.getLevelConfig(Integer.parseInt(selectedLevel));
                tryAgain.setCurrentLevel(Integer.parseInt(selectedLevel)); // Aggiorna anche il livello in TryAgain
                showLevelPopup(selectedLevel); // mostra il popup
                startGameWithConfig(config); // avvia il gioco
            } else {
                // Se il livello è già stato selezionato, avvia direttamente il gioco
                LevelManager.LevelConfig config = levelManager.getLevelConfig(Integer.parseInt(selectedLevel));
                startGameWithConfig(config);
            }
        });
    
        menuView.getControlsButton().addActionListener(e -> {
            switchToRulesView();
        });
    }

    private void setupRulesListeners() {
        rulesView.addBackButtonListener(e -> {
            switchToMenuView();
        });
    }

    @Override
    public void start() {
        frame.add(menuView);
        frame.setVisible(true);
    }

    @Override
    public void switchToMenuView() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuView);
        frame.revalidate();
        frame.repaint();
        currentLevel = ""; // resetta il livello selezionato

        if (dropTimer != null) {
            dropTimer.stop();
        }
    }

    @Override
    public void switchToRulesView() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(rulesView);
        frame.revalidate();
        frame.repaint();

        if (dropTimer != null) {
            dropTimer.stop();
        }
    }

    @Override
    public void switchToGameView() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gameView);
        frame.revalidate();
        frame.repaint();
        gameView.startGame();
    }

    public void resetGame() {
        // Logica per ripristinare la griglia
        grid.clear();  
        initializeGrid();  // Riempie la griglia con nuovi Puyo
    
        // Ripristinare il punteggio e altre variabili di stato
        progressBar.reset();  
    
        // Se il timer è attivo, fermalo e rinizializzalo
        if (dropTimer != null) {
            dropTimer.stop();  // Ferma il timer
        }
        
        LevelManager.LevelConfig config = levelManager.getLevelConfig(Integer.parseInt(currentLevel));
        dropTimer = new Timer(config.getDelay(), event -> {
            puyoDropper.fillGridRandomly(config.getPuyoCount());
        });
        dropTimer.setInitialDelay(2000);  // Ritardo prima di iniziare
        dropTimer.start();  // Avvia il timer
    
        // Avvia nuovamente il ciclo di gioco
        gameLoop.startGame();
    }
    
    

    @Override
    public void showLevelPopup(String level) {
        JDialog levelDialog = new JDialog(frame, "Livello Selezionato", true);
        levelDialog.setLayout(new BorderLayout());
        levelDialog.setSize(450, 250);
        levelDialog.setLocationRelativeTo(frame);
        levelDialog.setBackground(new Color(28, 28, 28));
        levelDialog.setUndecorated(true);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 5, true));
        panel.setLayout(new BorderLayout());
        panel.setOpaque(true);

        JLabel levelMessage = new JLabel(
                "<html><div style='text-align: center;'>Hai selezionato il livello:<br><span style='color: #FF3C00; font-size: 24px; font-weight: bold;'>"
                        + level + "</span></div></html>",
                JLabel.CENTER);
        levelMessage.setFont(new Font("Roboto", Font.PLAIN, 18));
        levelMessage.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(50, 50, 50));
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Roboto", Font.BOLD, 20));
        okButton.setBackground(new Color(70, 130, 180));
        okButton.setForeground(Color.WHITE);
        okButton.setPreferredSize(new Dimension(200, 60));
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        okButton.setFocusPainted(false);
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okButton.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                okButton.setBackground(new Color(70, 130, 180));
            }
        });

        okButton.addActionListener(e -> levelDialog.dispose());

        buttonPanel.add(okButton);

        panel.add(levelMessage, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        levelDialog.add(panel);
        levelDialog.setVisible(true);
    }
}