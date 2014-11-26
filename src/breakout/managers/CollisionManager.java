/*
 * CollisionManager.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Manages world interaction for collision/intersection events
 * Usage: Passed to GameObjects in their onIntersect method
 */

package breakout.managers;

public class CollisionManager extends MutableStateEventManager {
    /**
     * Constructs a new CollisionManager from the given GameManager
     * @param manager the GameManager to use
     */
    public CollisionManager(GameManager manager) {
        super(manager);
    }
}
