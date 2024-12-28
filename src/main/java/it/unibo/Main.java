package it.unibo;

import it.unibo.model.Menu;
import it.unibo.model.interfaces.MenuInterface;
import it.unibo.view.GameView;
import it.unibo.view.interfaces.GameViewInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] levels = {"Livello 1", "Livello 2", "Livello 3"};

        JFrame frame = new JFrame("Puyo Pop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        MenuInterface menu = new Menu(levels);

        GameViewInterface gameView = (GameViewInterface) new GameView();

        menu.getStartButton().addActionListener(e -> {
            String selectedLevel = menu.getSelectedLevel();

            JOptionPane.showMessageDialog(frame,
                "Hai selezionato: " + selectedLevel,
                "Livello Selezionato",
                JOptionPane.INFORMATION_MESSAGE);

            //cambia il contenuto del frame per mostrare la GameView
            frame.getContentPane().removeAll();
            frame.getContentPane().add((JPanel) gameView);
            frame.revalidate();
            frame.repaint();
            gameView.startGame();
        });

        menu.getControlsButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                "Comandi:\n- Frecce per muovere\n- Barra spaziatrice per sparare",
                "Comandi",
                JOptionPane.INFORMATION_MESSAGE);
        });

        frame.add((JPanel) menu);
        frame.setVisible(true);
    }
}
