package it.unibo.model.interfaces;

import it.unibo.model.Point2D;

public interface BulletModelInterface {
    void shootBullet(Point2D source, Point2D target);
    boolean targetReached();
    boolean updatePosition();
    boolean isActive();
    Point2D getCurrentPosition();
}
