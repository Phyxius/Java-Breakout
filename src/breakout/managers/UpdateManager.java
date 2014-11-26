/*
 * UpdateManager.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Manages world interaction on Update events
 * Usage: Passed to GameObjects during update events
 */

package breakout.managers;

public class UpdateManager extends MutableStateEventManager {
    /**
     * Constructs a new UpdateManager from the given GameManager
     * @param manager the GameManager to use
     */
    public UpdateManager(GameManager manager) {
        super(manager);
    }

    /**
     * Returns whether a key is pressed (true) or not (false)
     * @param key the key whose state is to be returned
     *            Must be a VK_ constant from KeyEvent
     * @return the state of the key
     */
    public boolean getKeyState(int key) {
        return manager.getKeyState(key);
    }
}
