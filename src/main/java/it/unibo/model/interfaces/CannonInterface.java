package it.unibo.model.interfaces;

public interface CannonInterface {
    void increaseCharge();
    void decreaseCharge();
    int getChargeLevel();
    void resetCharge();
    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();
    int getX();
    int getY();

}
