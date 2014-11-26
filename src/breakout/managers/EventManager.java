/*
 * EventManager.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: superclass for all EventManagers. Includes non-mutating methods.
 * Usage: Inherited by other managers
 */

package breakout.managers;

import breakout.gameobjects.Object2D;

import java.awt.*;

public abstract class EventManager {
    protected final GameManager manager;

    /**
     * Constructs a new EventManager with the given GameManager
     * @param manager the GameManager to use
     */
    public EventManager(GameManager manager) {
        this.manager = manager;
    }

    /**
     * @return true of debug mode is enabled, false otherwise
     */
    public boolean isDebugEnabled() {
        return manager.isDebugMode();
    }

    /**
     * @return the width of the current GameArea
     */
    public int getGameAreaWidth() {
        return manager.getGameArea().getWidth();
    }

    /**
     * @return the height of the current GameArea
     */
    public int getGameAreaHeight() {
        return manager.getGameArea().getHeight();
    }

    /**
     * @return the current GameArea as a Rectangle
     */
    public Rectangle getGameAreaRectangle() {
        return manager.getGameArea().getAsRectangle();
    }

    /**
     * Returns whether the given GameObject is out of the GameArea bounds
     * @param obj the object to check
     * @return true if it is out of bounds, false otherwise
     */
    public boolean isOutOfBounds(Object2D obj) {
        return manager.isOutOfBounds(obj);
    }

    /**
     * @return the current score
     */
    public int getScore() {
        return manager.getScore();
    }

    /**
     * @return returns the current life count
     */
    public int getLives() {
        return manager.getLives();
    }
}
