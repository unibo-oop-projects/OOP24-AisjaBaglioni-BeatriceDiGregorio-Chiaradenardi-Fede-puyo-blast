package it.unibo.model.interfaces;

public interface PuyoInterface {
    String getColor();
    void setColor(String color);
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    boolean isFalling();
    void setFalling(boolean isFalling);
    void moveDown();
    boolean checkCollision(PuyoInterface[][] grid);
    void explode();
    void updateGrid(PuyoInterface[][] grid);
}
