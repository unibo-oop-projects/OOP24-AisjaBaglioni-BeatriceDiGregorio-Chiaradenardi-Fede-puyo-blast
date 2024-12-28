package it.unibo;

import it.unibo.model.Menu;
import it.unibo.view.GameView;
import it.unibo.view.MenuRules;
import it.unibo.view.interfaces.GameViewInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] levels = {"Livello 1", "Livello 2", "Livello 3"};

        JFrame frame = new JFrame("Puyo Pop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Menu menuView = new Menu(levels);
        MenuRules rulesView = new MenuRules();
        GameViewInterface gameView = new GameView();

        //schermata del menù iniziale
        frame.add(menuView);
        frame.setVisible(true);

        //per passare alla schermata del gioco
        menuView.getStartButton().addActionListener(e -> {
            String selectedLevel = menuView.getSelectedLevel();
            
            //pop up per liv selezionato
            JOptionPane.showMessageDialog(
                frame,
                "Hai selezionato: " + selectedLevel,
                "Livello Selezionato",
                JOptionPane.INFORMATION_MESSAGE
            );
        
            //cambia la schermata per mostrare la GameView
            frame.getContentPane().removeAll();
            frame.getContentPane().add((JPanel) gameView);
            frame.revalidate();
            frame.repaint();
            gameView.startGame();
        });

        //per passare alla schermata regole/comandi
        menuView.getControlsButton().addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(rulesView);
            frame.revalidate();
            frame.repaint();
        });

        //per tornare al menù principale
        rulesView.addBackButtonListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(menuView);
            frame.revalidate();
            frame.repaint();
        });
    }
}
