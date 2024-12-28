package it.unibo;

import javax.swing.*;
import it.unibo.model.Menu;
import it.unibo.model.interfaces.MenuInterface;

public class Main {
    public static void main(String[] args) {
        String[] levels = {"Livello 1", "Livello 2", "Livello 3"};

        MenuInterface menu = new Menu(levels);

        menu.getStartButton().addActionListener(e -> {
            String selectedLevel = menu.getSelectedLevel();
            JOptionPane.showMessageDialog(null, "Hai selezionato: " + selectedLevel);
            //qui puoi avviare il gioco per il livello selezionato
        });

        menu.getControlsButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(null, 
                "Comandi:\n- Frecce per muovere\n- Barra spaziatrice per sparare");
        });

        menu.display();
    }
}
