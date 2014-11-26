/*
 * DrawingManager.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Manages world interaction on draw events
 * Usage: Passed to GameObjects during draw events
 */

package breakout.managers;

public class DrawingManager extends EventManager {
    /**
     * Constructs a new DrawingManager from the given GameManager
     * @param manager the GameManager to use
     */
    public DrawingManager(GameManager manager) {
        super(manager);
    }
}
