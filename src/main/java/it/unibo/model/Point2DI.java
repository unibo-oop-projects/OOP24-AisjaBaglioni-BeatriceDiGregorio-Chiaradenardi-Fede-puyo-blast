//AISJA 

package it.unibo.model;

public record Point2DI(int x, int y) {

    public static Point2DI add(Point2DI a, Point2DI b) {
        return new Point2DI(a.x + b.x, a.y + b.y);
    }

    public static Point2DI sub(Point2DI a, Point2DI b) {
        return new Point2DI(a.x - b.x, a.y - b.y);
    }

    public static Point2DI neg(Point2DI a) {
        return new Point2DI(-a.x, -a.y);
    }

    public static Point2DI mul(Point2DI a, int k) {
        return new Point2DI(a.x * k, a.y * k);
    }

    public static Point2DI div(Point2DI a, int k) {
        return new Point2DI(a.x / k, a.y / k);
    }

    public static Point2D toPoint2D(Point2DI a) {
        return new Point2D(a.x, a.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point2DI other = (Point2DI) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return x * 10007 + y;
    }
}
