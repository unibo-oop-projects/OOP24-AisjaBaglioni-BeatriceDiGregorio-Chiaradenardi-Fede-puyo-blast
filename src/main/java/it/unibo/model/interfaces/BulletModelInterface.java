package it.unibo.model.interfaces;

import it.unibo.model.Point2D;

/**
 * Interface representing the bullet model in the game.
 * Provides methods for updating the position and the status of the bullet.
 */
public interface BulletModelInterface {
    /**
     * Initializes the bullet and shoots it towards the grid
     * 
     * @param source The source position of the bullet
     * @param target The target position of the bullet
     */
    void shootBullet(Point2D source, Point2D target);

    /**
     * Checks if the bullet's animation has ended.
     * 
     * @return {@code true} if the bullet is still in motion, {@code false}
     *         otherwise.
     */
    boolean targetReached();

    /**
     * Updates the bullet's life time if the bullet is still active.
     * 
     * @return {@code true} if the bullet can still update its position,
     *         {@code false} otherwise.
     */

    boolean updatePosition();

    /**
     * Checks if the bullet is still active and thus being animated.
     * 
     * @return {@code true} if the bullet is active, {@code false} otherwise.
     */
    boolean isActive();

    /**
     * Calculates the current position of the projectile
     * along the trajectory between source and target, based on elapsed time.
     * It uses a linear interpolation with the alpha value:
     * alpha = ticks / ANIMATIONTIME which varies from 0 (start) to 1 (finish).
     * 
     * @return the current position in {@link Point2D}
     */
    Point2D getCurrentPosition();
}
