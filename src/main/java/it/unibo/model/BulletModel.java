//AISJA

package it.unibo.model;

public class BulletModel {
    private Point2D source;
    private Point2D target;
    private boolean active;
    private int ticks;
    private static final int ANIMATIONTIME = 15;

    public BulletModel() {
        this.source = null;
        this.target = null;
        this.active = false;
        this.ticks = 0;
    }

    public void shootBullet(Point2D source, Point2D target) {
        this.source = source;
        this.target = target;
        this.active = true;
        this.ticks = 0;
    }

    public boolean targetReached() {
        if (this.ticks >= ANIMATIONTIME) {
            this.active = false;
            return true;
        }
        return false;
    }

    public boolean updatePosition() {
        if (isActive()) {
            this.ticks++;
            if (this.ticks > ANIMATIONTIME) {
                this.active = false;
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isActive() {
        return this.active;
    }

    public Point2D getCurrentPosition() {
        double alpha = ((double) this.ticks) / ((double) ANIMATIONTIME);
        Point2D left = Point2D.mul(source, 1.0 - alpha);
        Point2D right = Point2D.mul(target, alpha);
        return Point2D.add(left, right);
    }
}