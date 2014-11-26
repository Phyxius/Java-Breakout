/*
 * MovableObject.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Superclass for movable objects
 * Usage: Implement
 */

package breakout.gameobjects;

import java.awt.*;
import java.awt.geom.Point2D;

public interface MovableObject {
    /**
     * @return the current X speed
     */
    int getXSpeed();

    /**
     * @return the current Y speed
     */
    int getYSpeed();

    /**
     * Sets the X speed to the given value
     * @param xSpeed the speed to set
     */
    void setXSpeed(int xSpeed);

    /**
     * Sets the Y speed to the given value
     * @param ySpeed the speed to set
     */
    void setYSpeed(int ySpeed);

    /**
     * @return the current speed as a vector (Point)
     */
    default Point2D.Double getAsPoint() {
        return new Point2D.Double(getXSpeed(), getYSpeed());
    }

    /**
     * Sets the current speed to the given vector (Point)
     * @param vector the vector to set
     */
    default void setSpeed(Point vector) {
        setXSpeed((int)vector.getX());
        setYSpeed((int)vector.getY());
    }
}
