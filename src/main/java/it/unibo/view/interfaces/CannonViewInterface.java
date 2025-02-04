package it.unibo.view.interfaces;

import it.unibo.model.Point2DI;

public interface CannonViewInterface {

    int getImageIndexForAngle(final double angle);
    Point2DI getCenter();
}
