package it.unibo.model;

import it.unibo.model.interfaces.PuyoInterface;

public class Puyo implements PuyoInterface {
    private String color;
    private int x;
    private int y;
    private boolean isFalling;

    public Puyo(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.isFalling = true;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean isFalling() {
        return isFalling;
    }

    @Override
    public void setFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    @Override
    public void moveDown() {
        if (isFalling) {
            y += 1;
        }
    }

    @Override
    public boolean checkCollision(PuyoInterface[][] grid) {
        if (y + 1 >= grid.length || grid[y + 1][x] != null) {
            isFalling = false;
            return true;
        }
        return false;
    }

    @Override
    public void explode() {
        System.out.println("Puyo at (" + x + ", " + y + ") exploded!");
    }

    @Override
    public void updateGrid(PuyoInterface[][] grid) {
        grid[y][x] = null;
        if (isFalling) {
            grid[y + 1][x] = this;
        }
    }
}
