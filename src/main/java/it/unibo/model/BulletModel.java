//AISJA

package it.unibo.model;

public class BulletModel {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean active;

    public BulletModel(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.active = false;
    }

    public void shootBullet() {
        this.active = true;
    }

    public boolean targetReached() {
        if (this.x == this.dx && this.y == this.dy) {
            this.active = false;
            return true;
        }
        return false;
    }

    // se x è a sx del target, allora devo incrementare verso destra
    // se x è a dx del target, devo decrementare verso sinistra
    // y è sempre più sopra
    public int getDistanceX() {
        return this.dx - this.x;
    }

    public int getDistanceY() {
        return this.dy - this.y;
    }

    public void updatePosition() {
        if (!this.active) {
            return;
        }
        if (!this.targetReached()) {
            if (this.getDistanceX() > 0) {
                this.x++;
            } else if (this.getDistanceX() < 0) {
                this.x--;
            }
            this.y++;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isActive() {
        return this.active;
    }
}
