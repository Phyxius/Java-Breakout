/*
 * BasicGameArea.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Basic GameArea object for testing
 */

package breakout;

public class BasicGameArea implements GameArea {
    private final int width, height;

    /**
     * Constructs a new BasicGameArea with the given width and height
     * @param width the width to use
     * @param height the height to use
     */
    public BasicGameArea(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width of the GameArea
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the GameArea
     */
    @Override
    public int getHeight() {
        return height;
    }
}
