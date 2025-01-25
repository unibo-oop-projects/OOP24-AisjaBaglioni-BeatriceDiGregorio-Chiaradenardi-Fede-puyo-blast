package it.unibo.view.interfaces;

import java.awt.Graphics;

public interface CannonViewInterface {

    void draw(Graphics g);

    int getImageIndexForAngle(final double angle);

}
