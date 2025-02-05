//AISJA

package it.unibo.model;

import it.unibo.model.interfaces.ScaleInterface;

/**
 * A utility class representing the size of the window,
 * and the game graphics scaling,
 * being the unit of measurement adopted by all the views
 */
public class Scale implements ScaleInterface {
    /*
     * A single integer value, since the window is a square
     */
    private int scale;

    /**
     * Constructs a new scale
     * 
     * @param scale Width and height of the window
     */
    public Scale(int scale) {
        this.scale = scale;
    }

    /**
     * Constructs a new scale with a default size of 700x700
     */
    public Scale() {
        this.scale = 700;
    }

    /**
     * A method to get the scale's width and height from outside.
     * 
     * @return the scale size
     */
    @Override
    public int getScale() {
        return this.scale;
    }
}
