package it.unibo.model;

public class Rectangle {
    public Point2DI upleft;
    public Point2DI lowright;

    public Rectangle(Point2DI upleft, Point2DI lowright) {
        this.upleft = upleft;
        this.lowright = lowright;
    }

    public boolean isInside(Point2DI pos) {
        return (pos.x() >= upleft.x() && pos.x() <= this.lowright.x() && pos.y() <= this.lowright.y()
                && pos.y() >= this.upleft.y());
    }
}
