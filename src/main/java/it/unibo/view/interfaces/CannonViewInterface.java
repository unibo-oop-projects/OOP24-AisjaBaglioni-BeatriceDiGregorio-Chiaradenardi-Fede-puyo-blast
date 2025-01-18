package it.unibo.view.interfaces;

import java.awt.Graphics;

public interface CannonViewInterface {
    void updateImage();

    void draw(final Graphics g);

    void setCannonPosition(int newX, int newY);

    void setCannonAngle(int newAngle);

    int getCannonWidth();

    int getCannonHeight();

}
