//chiara
package it.unibo.controller;

import it.unibo.controller.interfaces.ScreenManagerInterface;
import it.unibo.model.BulletModel;
import it.unibo.model.CannonModel;
import it.unibo.model.ExitModel;
import it.unibo.model.Grid;
import it.unibo.model.KeyboardModel;
import it.unibo.model.Menu;
import it.unibo.model.PauseModel;
import it.unibo.model.ProgressBarModel;
import it.unibo.model.Puyo;
import it.unibo.model.Scale;
import it.unibo.model.ScoreModel;
import it.unibo.model.StatusModel;
import it.unibo.model.TryAgainModel;
import it.unibo.view.CannonView;
import it.unibo.view.EndView;
import it.unibo.view.ExitView;
import it.unibo.view.GameView;
import it.unibo.view.MenuRules;
import it.unibo.view.PauseView;
import it.unibo.view.PuyoRenderer;
import it.unibo.view.ScoreView;
import it.unibo.view.TryAgainView;

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
    private final ExitModel exitModel;
    private final ExitController exitController;
    private final TryAgainView tryAgainView;
    private final TryAgainModel tryAgainModel;
    private final TryAgainController tryAgainController;
    private final ScoreModel scoreModel;
    private final ScoreController scoreController;
    private final ScoreView scoreView;
    private final PuyoRenderer puyoRenderer;
    private final FreezeController freezeController;
    private final StatusModel statusModel;
    private final EndController endController;
    private final EndView endView;
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

        this.grid = new Grid(8, 8);
        this.levelManager = new LevelManager();
        this.freezeController = new FreezeController(grid);
        this.exitModel = new ExitModel();
        this.exitController = new ExitController(exitModel, this);
        this.exitView = new ExitView(scale, exitController);
        this.tryAgainModel = new TryAgainModel();
        this.tryAgainController = new TryAgainController(levelManager, this, tryAgainModel);
        this.tryAgainView = new TryAgainView(scale, tryAgainController);
        this.menuView = new Menu(levels, scale);
        this.rulesView = new MenuRules(scale);
        this.cannonModel = new CannonModel();
        this.progressBarModel = new ProgressBarModel();
        this.progressBar = new ProgressBarController(this.progressBarModel);
        this.bulletModel = new BulletModel();
        this.cannonView = new CannonView(this.scale, cannonModel);
        this.pauseModel = new PauseModel();
        this.pauseController = new PauseController(pauseModel);
        this.pauseView = new PauseView(this.scale, pauseModel, pauseController);
        this.scoreModel = new ScoreModel();
        this.scoreController = new ScoreController(scoreModel);
        this.scoreView = new ScoreView(scoreModel, scale);
        this.puyoRenderer = new PuyoRenderer(scale);
        this.statusModel = new StatusModel();
        this.endController = new EndController(grid, scoreModel, statusModel);
        this.endView = new EndView(statusModel, scale, scoreModel);
        this.gameView = new GameView(grid, scale, puyoRenderer, cannonModel, cannonView, progressBarModel, bulletModel, pauseView, tryAgainView,
            exitView, scoreView, endView, this);
        this.gameLoop = new GameLoop(this.gameView,this.pauseModel, new HashSet<>());
        this.keyboardModel = new KeyboardModel();
        this.cannon = new CannonController(this.cannonModel, this.keyboardModel, this.progressBar);
        this.bulletController = new BulletController(bulletModel, grid, keyboardModel, progressBar, cannonView, scoreController, scale);
        this.puyoExplosionController = new PuyoExplosionController(grid);
        this.gameLoop.addTickListener(this.cannon);
        this.gameLoop.addTickListener(this.progressBar);
        this.gameLoop.addTickListener(this.bulletController);
        this.gameLoop.addTickListener(this.puyoExplosionController);
        this.gameLoop.addTickListener(this.puyoRenderer);
        this.gameLoop.addTickListener(this.freezeController);
        this.gameLoop.addTickListener(this.endController);
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
        this.gameLoop.startGame();
    }

    private void configureInputHandlers() {
        KeyboardController keyboardController = new KeyboardController(this.keyboardModel);
        gameView.addKeyListener(keyboardController);
        gameView.setFocusable(true);
        gameView.addKeyListener(this.pauseController);
    }

    private void setupMenuListeners() {
        // Quando avvii un livello dal menu
        menuView.getStartButton().addActionListener(e -> {
            String selectedLevel = menuView.getSelectedLevel();
            if (!selectedLevel.equals(currentLevel)) {
                currentLevel = selectedLevel; // aggiorna il livello corrente
                LevelManager.LevelConfig config = levelManager.getLevelConfig(Integer.parseInt(selectedLevel));
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
        // Ferma qualsiasi attività in corso nel gioco
        if (dropTimer != null) {
            dropTimer.stop();  // Ferma il timer
        }
        
        // Resetta la griglia e la barra di progresso
        grid.clear();
        initializeGrid();  // Riempie la griglia con nuovi Puyo
        progressBar.reset();  // Reset del punteggio e della progress bar

        // Rimuovi i listener esistenti dal gameLoop
        gameLoop.removeTickListener(puyoDropper);

        // Imposta il menu come vista corrente
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuView);
        frame.revalidate();
        frame.repaint();

        // Reset del livello selezionato
        currentLevel = ""; // Reset del livello selezionato
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
        
        // Fermare il dropTimer se è attivo
        if (dropTimer != null) {
            dropTimer.stop();
            dropTimer = null;  // Azzeriamo il timer
        }
        
        // Rimuovi i listener esistenti dal gameLoop
        gameLoop.removeTickListener(puyoDropper); 
        
        // Recupera la configurazione del livello corrente
        LevelManager.LevelConfig config = levelManager.getLevelConfig(Integer.parseInt(currentLevel));
        
        // Rinnova il puyoDropper con le configurazioni del livello
        puyoDropper = new PuyoDropper(grid, config);  // Assicurati che il PuyoDropper venga ricreato con la nuova configurazione
        this.gameLoop.addTickListener(puyoDropper);
        
        // Avvia il nuovo dropTimer con il ritardo e il numero di Puyo corretti
        dropTimer = new Timer(config.getDelay(), event -> {
            puyoDropper.dropPuyo(); // Genera nuovi Puyo in base alla configurazione
        });
        
        // Avvia nuovamente il ciclo di gioco
        gameLoop.startGame();
    }
    
    
    
    @Override
    public void showLevelPopup(String level) {
        JDialog levelDialog = new JDialog(frame, "Livello Selezionato", true);
        levelDialog.setLayout(new BorderLayout());
        levelDialog.setSize(450, 250);
        levelDialog.setLocationRelativeTo(frame);
        levelDialog.setUndecorated(true);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(51,73,112));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        JLabel levelMessage = new JLabel(
            "<html><div style='text-align: center;'>Hai selezionato il livello:<br><span style='color: #FFFFFF; font-size: 24px; font-weight: bold;'>"
            + level + "</span></div></html>", JLabel.CENTER);
        levelMessage.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        levelMessage.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        okButton.setBackground(new Color(25,25,112));
        okButton.setForeground(Color.BLACK);
        okButton.setPreferredSize(new Dimension(200, 60));
        okButton.setFocusPainted(false);
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            private final Color normalColor = new Color(25,25,112);
            private final Color hoverColor = normalColor.darker();

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okButton.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okButton.setBackground(normalColor);
            }
        });

        okButton.addActionListener(e -> levelDialog.dispose());

        buttonPanel.add(okButton);

        panel.add(levelMessage, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        levelDialog.setContentPane(panel);
        levelDialog.setVisible(true);
    }

}