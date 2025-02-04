package it.unibo.model;

import it.unibo.model.interfaces.RectangleInterface;

public class Rectangle implements RectangleInterface {
    public Point2DI upleft;
    public Point2DI lowright;

    public Rectangle(Point2DI upleft, Point2DI lowright) {
        this.upleft = upleft;
        this.lowright = lowright;
    }

    @Override
    public boolean isInside(Point2DI pos) {
        return (pos.x() >= upleft.x() && pos.x() <= this.lowright.x() && pos.y() <= this.lowright.y()
                && pos.y() >= this.upleft.y());
    }
}
