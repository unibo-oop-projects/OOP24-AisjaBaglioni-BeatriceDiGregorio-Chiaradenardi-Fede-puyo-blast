//chiara
package it.unibo.controller;

import it.unibo.model.Menu;
import it.unibo.view.GameView;
import it.unibo.view.MenuRules;

import javax.swing.*;

public class ScreenManager {
    private final JFrame frame;
    private final Menu menuView;
    private final MenuRules rulesView;
    private final GameView gameView;

    public ScreenManager(String[] levels) {
        this.frame = new JFrame("Puyo Pop");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);

        this.menuView = new Menu(levels);
        this.rulesView = new MenuRules();
        this.gameView = new GameView();

        //configura i listener per il menu
        setupMenuListeners();

        //configura i listener per la schermata regole/comandi
        setupRulesListeners();
    }

    private void setupMenuListeners() {
        menuView.getStartButton().addActionListener(e -> {
            String selectedLevel = menuView.getSelectedLevel();
            //popup con il livello selezionato
            JOptionPane.showMessageDialog(
                frame,
                "Hai selezionato: " + selectedLevel,
                "Livello Selezionato",
                JOptionPane.INFORMATION_MESSAGE
            );
            //cambia schermata
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
        //schermata iniziale
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
}
