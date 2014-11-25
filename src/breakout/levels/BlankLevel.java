/*
 * BlankLevel.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: A Level object that returns a blank Iterable, for testing
 * purposes.
 * Usage: Use wherever a Level object is needed
 */

package breakout.levels;

import breakout.gameobjects.GameObject;

import java.util.Collections;

public class BlankLevel implements Level {
    /**
     * @return an empty Iterable
     */
    @Override
    public Iterable<GameObject> createObjects() {
        return Collections.emptyList();
    }
}
