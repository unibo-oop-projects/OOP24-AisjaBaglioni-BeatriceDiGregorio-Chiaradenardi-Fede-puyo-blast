package it.unibo.view.interfaces;

import it.unibo.model.Point2DI;

public interface ClickInterface {
    boolean isClicked(Point2DI pos);

    void doAction();

}