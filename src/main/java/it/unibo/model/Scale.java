//AISJA

package it.unibo.model;

import it.unibo.model.interfaces.ScaleInterface;

public class Scale implements ScaleInterface{
    private int scale;

    public Scale(int scale) {
        this.scale = scale;
    }

    public Scale() {
        this.scale = 700;
    }

    @Override
    public int getScale() {
        return this.scale;
    }
}
