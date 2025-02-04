package it.unibo.controller.interfaces;

import it.unibo.model.Point2DI;
import it.unibo.view.CannonView;

public interface BulletControllerInterface {

    void explodePuyos(Point2DI target);
    void setCannonView(CannonView cannonView);
}
