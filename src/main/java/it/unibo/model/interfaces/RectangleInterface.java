package it.unibo.model.interfaces;

import it.unibo.model.Point2DI;

/**
 * An interface representing a rectangle.
 * Provides a method for checking if a 2D point is inside the rectangle's area.
 */
public interface RectangleInterface {

    /**
     * Checks if the given point is inside the rectangle.
     * 
     * @param pos The Point2DI whose position needs to be checked
     * 
     * @return {@code true} if the area contains the point, {@code false} otherwise.
     */
    boolean isInside(Point2DI pos);
}
