//chiara
package it.unibo.controller;

import it.unibo.controller.interfaces.PuyoDropperInterface;
import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuyoDropper implements PuyoDropperInterface{
    private final Grid grid;
    private final GameView gameView;
    private final Random random;
    private final String[] colors = {"red", "blue", "green", "yellow", "purple", "cyan", "pink"};
    private final List<Puyo> puyosInGame; //per memorizzare i Puyo nella griglia
    
    public PuyoDropper(Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
        this.random = new Random();
        this.puyosInGame = new ArrayList<>();
    }

    //metodo per generare un Puyo casuale e farlo cadere
    public void spawnAndDropPuyo() {
        //genera un nuovo Puyo con un colore casuale
        String randomColor = colors[random.nextInt(colors.length)];

        //trova una colonna dove c'è spazio per far cadere un Puyo
        int startX = random.nextInt(grid.getCols()); //posizione casuale X
        int startY = findAvailableYPosition(startX); //trova la Y disponibile per il Puyo nella colonna

        if (startY != -1) {
            //aggiungi il Puyo nella posizione trovata
            Puyo puyo = new Puyo(randomColor, startX, startY);
            grid.addPuyo(puyo, startX, startY);
            puyosInGame.add(puyo); // Memorizza il Puyo in una lista

            //avvia il movimento del Puyo che cade in un nuovo thread
            new Thread(() -> dropPuyo(puyo)).start();
        }
    }

    //trova la prima posizione disponibile in una colonna a partire dalla riga più bassa
    @Override
    public int findAvailableYPosition(int x) {
        for (int y = grid.getRows() - 1; y >= 0; y--) { //controlla dalla riga più bassa
            if (grid.getPuyo(x, y) == null) {
                return y; //restituisce la prima riga vuota (dall'alto verso il basso)
            }
        }
        return -1; //se non c'è spazio (colonna piena)
    }

    //logica di caduta del Puyo
    @Override
    public void dropPuyo(Puyo puyo) {
        int posY = puyo.getY(); //posizione iniziale del Puyo
        boolean isFalling = true;

        while (isFalling) {
            if (posY < grid.getRows() - 1) {
                //verifica se la cella sotto è vuota
                if (grid.getPuyo(puyo.getX(), posY + 1) == null) {
                    grid.removePuyo(puyo.getX(), posY); //rimuove il Puyo dalla posizione precedente (per simulare movimento)
                    posY++; 
                    puyo.setY(posY); //aggiorna la posizione Y
                    grid.addPuyo(puyo, puyo.getX(), posY); //aggiungi il Puyo nella nuova posizione
                    gameView.repaint(); //rende la visualizzazione aggiornata

                    try {
                        Thread.sleep(150); //ritardo per l'animazione
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //se la cella sotto è occupata, il Puyo si ferma
                    isFalling = false;
                }
            } else {
                //se il Puyo raggiunge la fine della griglia, si ferma
                isFalling = false;
            }
        }
    }

    //metodo per ottenere la lista di Puyo memorizzati nella griglia
    public List<Puyo> getPuyosInGame() {
        return puyosInGame;
    }
}
