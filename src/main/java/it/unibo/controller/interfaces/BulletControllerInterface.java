package it.unibo.controller.interfaces;

import it.unibo.model.Point2DI;
import it.unibo.view.CannonView;

/** An interface representing the controller for the bullet mechanics. */
public interface BulletControllerInterface {

    /**
     * A method implementing a BFS to search for adjacent Puyos and explode them
     */
    void explodePuyos(Point2DI target);

    /**
     * A method for setting the {@link CannonView}, short after the
     * {@link ControllerStorage}
     * is created.
     */
    void setCannonView(CannonView cannonView);
}
