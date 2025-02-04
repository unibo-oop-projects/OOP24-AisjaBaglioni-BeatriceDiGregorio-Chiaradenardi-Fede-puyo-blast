// CHIARA
package it.unibo.controller;

import it.unibo.GameEvent;
import it.unibo.GameListener;
import it.unibo.controller.interfaces.MenuManagerInterface;
import it.unibo.model.Scale;
import it.unibo.view.Menu;
import it.unibo.view.MenuRules;

import javax.swing.*;
import java.awt.*;

public class MenuManager extends JInternalFrame implements MenuManagerInterface{
    private final Menu menuView;
    private final MenuRules rulesView;
    private LevelManager levelManager;
    private final GameListener gameListener;

    public MenuManager(GameListener gameListener, Scale scale) {
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        String[] levels = { "1", "2", "3" };
        this.gameListener = gameListener;

        this.menuView = new Menu(levels, scale);
        this.rulesView = new MenuRules(scale);

        this.levelManager = new LevelManager();
        setupMenuListeners();
        setupRulesListeners();
        this.start();
    }

    // Allows to start game from the menu, via an object GameEvent
    private void startGameWithConfig(LevelManager.LevelConfig config) {
        GameEvent event = new GameEvent(this, config);
        gameListener.startGame(event);
    }

    private void setupMenuListeners() {
        // Quando avvii un livello dal menu
        menuView.getStartButton().addActionListener(e -> {
            String selectedLevel = menuView.getSelectedLevel();
            LevelManager.LevelConfig config = levelManager.getLevelConfig(Integer.parseInt(selectedLevel));
            showLevelPopup(selectedLevel, config); // mostra il popup
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
        this.add(menuView);
        this.setVisible(true);
    }

    @Override
    public void switchToMenuView() {
        // Imposta il menu come vista corrente
        this.getContentPane().removeAll();
        this.getContentPane().add(menuView);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void switchToRulesView() {
        this.getContentPane().removeAll();
        this.getContentPane().add(rulesView);
        this.revalidate();
        this.repaint();
    }

    // The "level selected" pop-up is now a JPanel instead of a JDialog
    // Due to the JInternalFrame limitations
    @Override
    public void showLevelPopup(String level, LevelManager.LevelConfig config) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog levelDialog = new JDialog();
        levelDialog.setLayout(new BorderLayout());
        levelDialog.setSize(450, 250);
        levelDialog.setLocationRelativeTo(topFrame);
        levelDialog.setUndecorated(true);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(51, 73, 112));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        JLabel levelMessage = new JLabel(
                "<html><div style='text-align: center;'>Hai selezionato il livello:<br><span style='color: #FFFFFF; font-size: 24px; font-weight: bold;'>"
                        + level + "</span></div></html>",
                JLabel.CENTER);
        levelMessage.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        levelMessage.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        okButton.setBackground(new Color(25, 25, 112));
        okButton.setForeground(Color.BLACK);
        okButton.setPreferredSize(new Dimension(200, 60));
        okButton.setFocusPainted(false);
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            private final Color normalColor = new Color(25, 25, 112);
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

        okButton.addActionListener(e -> {
            levelDialog.dispose();
            startGameWithConfig(config); // avvia il gioco
        });

        buttonPanel.add(okButton);

        panel.add(levelMessage, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        levelDialog.setContentPane(panel);
        levelDialog.setVisible(true);
    }

}