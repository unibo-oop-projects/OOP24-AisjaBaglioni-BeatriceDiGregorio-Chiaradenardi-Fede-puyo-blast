//chiara
package it.unibo.controller;

import it.unibo.model.Grid;
import it.unibo.model.Menu;
import it.unibo.model.Puyo;
import it.unibo.view.GameView;
import it.unibo.view.MenuRules;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ScreenManager {
    private final JFrame frame;
    private final Menu menuView;
    private final MenuRules rulesView;
    private final GameView gameView;

    private Timer dropTimer; //timer per far cadere i Puyo
    private Grid grid; 
    private PuyoDropper puyoDropper; 

    public ScreenManager(String[] levels) {
        this.frame = new JFrame("Puyo Pop");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);

        grid = new Grid(6, 6); 
        this.menuView = new Menu(levels);
        this.rulesView = new MenuRules();
        this.gameView = new GameView(grid);

        puyoDropper = new PuyoDropper(grid, gameView); 

        setupMenuListeners();
        setupRulesListeners();
        //inizializza la griglia con 2 righe di Puyo
        initializeGrid();
        //timer per la caduta dei Puyo (1 secondi per un Puyo che cade)
        dropTimer = new Timer(1000, e -> puyoDropper.spawnAndDropPuyo());
        dropTimer.start(); // Avvia il timer
    }

    //metodo per inizializzare le due righe di Puyo
    private void initializeGrid() {
        String[] initialColors = {"red", "blue", "green", "yellow", "purple", "cyan"};
        for (int y = 4; y < 6; y++) { //la griglia ha 6 righe (0-5), quindi le righe 4 e 5 sono le ultime
            for (int x = 0; x < grid.getCols(); x++) {
                String randomColor = initialColors[new Random().nextInt(initialColors.length)];
                Puyo puyo = new Puyo(randomColor, x, y);
                grid.addPuyo(puyo, x, y); //posiziona un Puyo nelle righe 4 e 5
            }
        }
    }

    private void setupMenuListeners() {
        menuView.getStartButton().addActionListener(e -> {
            String selectedLevel = menuView.getSelectedLevel();
            showLevelPopup(selectedLevel);
            switchToGameView();
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

    public void start() {
        frame.add(menuView);
        frame.setVisible(true);
    }

    private void switchToMenuView() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuView);
        frame.revalidate();
        frame.repaint();
    }

    private void switchToRulesView() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(rulesView);
        frame.revalidate();
        frame.repaint();
    }

    private void switchToGameView() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gameView);
        frame.revalidate();
        frame.repaint();
        gameView.startGame();
    }

    private void showLevelPopup(String level) {
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

        JLabel levelMessage = new JLabel("<html><div style='text-align: center;'>Hai selezionato il livello:<br><span style='color: #FF3C00; font-size: 24px; font-weight: bold;'>" + level + "</span></div></html>", JLabel.CENTER);
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
