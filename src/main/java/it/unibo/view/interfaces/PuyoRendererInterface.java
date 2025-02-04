package it.unibo.view.interfaces;

import java.awt.Graphics;

import it.unibo.model.Grid;

public interface PuyoRendererInterface {
    int sameColorNeighbour(Grid grid, int row, int col, String color);
    void render(Graphics g, Grid grid, int row, int col);
}
