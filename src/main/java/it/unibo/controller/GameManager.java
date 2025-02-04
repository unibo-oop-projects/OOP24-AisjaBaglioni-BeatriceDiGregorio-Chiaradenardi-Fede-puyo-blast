// AISJA
package it.unibo.controller;

import it.unibo.GameListener;
import it.unibo.model.ModelStorage;
import it.unibo.model.Scale;
import it.unibo.view.GameView;

import javax.swing.*;

public class GameManager extends JInternalFrame {
    private final ModelStorage modelStorage;
    private final ControllerStorage controllerStorage;
    private final GameView gameView;

    public GameManager(GameListener gameListener, Scale scale, LevelManager.LevelConfig levelConfig) {
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.modelStorage = new ModelStorage(scale);
        this.controllerStorage = new ControllerStorage(modelStorage, gameListener, levelConfig);
        this.gameView = new GameView(modelStorage, controllerStorage);

        this.controllerStorage.linkGameView(gameView);

        controllerStorage.start();
        this.add(gameView);
        gameView.setVisible(true);
    }
}