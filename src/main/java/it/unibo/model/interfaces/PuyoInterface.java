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
    boolean checkCollision(GridInterface grid);
    void explode();
    void updateGrid(GridInterface grid);
    long getIdentifier();
}
