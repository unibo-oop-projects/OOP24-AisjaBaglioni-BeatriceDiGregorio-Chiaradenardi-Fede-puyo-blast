package it.unibo.model.interfaces;

/**
 * An interface representing the scale, aka the size of the square game window
 * and the unit of measurement for all its elements
 */
public interface ScaleInterface {
    /**
     * A method to get the scale's width and height from outside.
     * 
     * @return the scale size
     */
    int getScale();
}
