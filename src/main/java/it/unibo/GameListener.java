//AISJA

package it.unibo;

import java.util.EventListener;

// Listens to an event for starting the game or returning to the menu
public interface GameListener extends EventListener {
    void startGame(GameEvent e);

    void goToMainMenu(GameEvent e);
}