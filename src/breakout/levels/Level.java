/*
 * Level.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Interface for Levels that allows the game to load them on demand
 * Level instances MUST be stateless, i.e. calling createObjects() repeatedly
 * should not have any side effects
 * Usage: Inherit and implement createObjects, and optionally
 * getNextLevelInSequence.
 */

package breakout.levels;

import breakout.gameobjects.GameObject;


public interface Level {
    /**
     * Creates the objects in the level, minus the paddle and ball, then
     * returns an Iterable of them.
     * just a bunch of bricks.
     * @return the Iterable
     */
    Iterable<GameObject> createObjects();

    /**
     * Returns the next Level instance in the level sequence. E.g. the first
     * level of the game would return the second Level when this method is
     * called.
     * By default this returns the same object.
     * @return the next level in the sequence, 'this' by default.
     */
    default Level getNextLevelInSequence() {
        return this;
    }
}
