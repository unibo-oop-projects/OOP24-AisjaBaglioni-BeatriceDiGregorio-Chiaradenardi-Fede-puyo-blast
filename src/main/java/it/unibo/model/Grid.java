//FEDE
//gestisce la griglia e la disposizione dei puyos

package it.unibo.model;

import it.unibo.model.interfaces.GridInterface;
import it.unibo.model.interfaces.PuyoInterface;

public class Grid implements GridInterface {
    private PuyoInterface[][] grid; // Matrice bidimensionale per i Puyo (uso PuyoInterface)
    private final int rows; // Numero di righe
    private final int cols; // Numero di colonne

    // Costruttore
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new PuyoInterface[rows][cols]; // Array di PuyoInterface
    }

    // ritorna il numero di righe
    @Override
    public int getRows() {
        return rows;
    }

    // ritorna il numero di colonne
    @Override
    public int getCols() {
        return cols;
    }

    // aggiunge un puyo alla griglia in una posizione specifica
    @Override
    public boolean addPuyo(PuyoInterface puyo, int x, int y) { // Usa PuyoInterface
        if (isValidPosition(x, y) && grid[y][x] == null) {
            grid[y][x] = puyo;
            return true;
        }
        return false;
    }

    // rimuove un puyo dalla griglia in una posizione specifica
    @Override
    public void removePuyo(int x, int y) {
        if (isValidPosition(x, y)) {
            grid[y][x] = null;
        }
    }

    // controlla se una posizione è valida
    @Override
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }

    // ritorna il puyo in una posizione specifica
    @Override
    public PuyoInterface getPuyo(int x, int y) { // Restituisce PuyoInterface
        if (isValidPosition(x, y)) {
            return grid[y][x];
        }
        return null;
    }

    // controlla se una colonna è piena
    @Override
    public boolean isColumnFull(int x) {
        for (int y = 0; y < rows; y++) {
            if (grid[y][x] == null) {
                return false;
            }
        }
        return true;
    }

    // controllo se una riga è piena
    @Override
    public boolean isRowFull(int y) {
        for (int x = 0; x < cols; x++) {
            if (grid[y][x] == null) {
                return false;
            }
        }
        return true;
    }

    // controlla se la griglia è piena
    @Override
    public boolean isGridFull() {
        for (int x = 0; x < cols; x++) {
            if (!isColumnFull(x)) {
                return false;
            }
        }
        return true;
    }

    // aggiorna la griglia facendo cadere i puyos nelle posizioni pù basse
    // disponibili
    @Override
    public void updateGrid() {

    }

    @Override
    public void printGrid() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] != null) {
                    System.out.print(grid[y][x].getColor().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[x][y] = null; // Imposta ogni cella a null
            }
        }
    }

}
