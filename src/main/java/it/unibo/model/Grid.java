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

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public boolean addPuyo(PuyoInterface puyo, int x, int y) { // Usa PuyoInterface
        if (isValidPosition(x, y) && grid[y][x] == null) {
            grid[y][x] = puyo;
            return true;
        }
        return false;
    }

    @Override
    public void removePuyo(int x, int y) {
        if (isValidPosition(x, y)) {
            grid[y][x] = null;
        }
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }

    @Override
    public PuyoInterface getPuyo(int x, int y) { // Restituisce PuyoInterface
        if (isValidPosition(x, y)) {
            return grid[y][x];
        }
        return null;
    }

    @Override
    public boolean isColumnFull(int x) {
        for (int y = 0; y < rows; y++) {
            if (grid[y][x] == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isGridFull() {
        for (int x = 0; x < cols; x++) {
            if (!isColumnFull(x)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateGrid() {
        for (int x = 0; x < cols; x++) {
            for (int y = rows - 1; y >= 0; y--) {
                if (grid[y][x] == null) {
                    for (int k = y - 1; k >= 0; k--) {
                        if (grid[k][x] != null) {
                            grid[y][x] = grid[k][x];
                            grid[k][x] = null;
                            break;
                        }
                    }
                }
            }
        }
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
}
