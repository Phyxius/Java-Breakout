/*
 * GameArea.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: interface representing rectangular game areas
 * Usage: Inherit
 */

package breakout;

import java.awt.*;

public interface GameArea {

    /** @return Width of the playing area */
    int getWidth();

    /** @return Height of the playing area */
    int getHeight();

    /**
     * @return the GameArea as a rectangle in the first quadrant, including
     * the origin
     */
    default Rectangle getAsRectangle() {
        return new Rectangle(0, 0, getWidth(), getHeight());
    }
}