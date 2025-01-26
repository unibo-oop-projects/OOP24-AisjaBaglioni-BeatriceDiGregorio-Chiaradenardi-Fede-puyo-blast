//AISJA 

package it.unibo.model;

public record Point2D(double x, double y) {

    public static Point2D add(Point2D a, Point2D b) {
        return new Point2D(a.x + b.x, a.y + b.y);
    }

    public static Point2D sub(Point2D a, Point2D b) {
        return new Point2D(a.x - b.x, a.y - b.y);
    }

    public static Point2D neg(Point2D a) {
        return new Point2D(-a.x, -a.y);
    }

    public static Point2D mul(Point2D a, double k) {
        return new Point2D(a.x * k, a.y * k);
    }

    public static Point2D div(Point2D a, double k) {
        return new Point2D(a.x / k, a.y / k);
    }

}