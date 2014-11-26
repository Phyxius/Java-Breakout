/*
 * MutableStateEventManager.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Event manager for events that are allowed to modify the state of the
 * game world
 * Usage: Inherited by other EventManagers
 */

package breakout.managers;

import breakout.gameobjects.GameObject;

import java.util.Collection;


public class MutableStateEventManager extends EventManager {
    /**
     * Constructs a new MutableStateEventManager using the specified GameManager
     * @param manager the manager to use
     */
    public MutableStateEventManager(GameManager manager) {
        super(manager);
    }

    /**
     * Removes the specified GameObject from the game world
     * @param obj the object to remove
     */
    public void remove(GameObject obj) {
        manager.remove(obj);
    }

    /**
     * Removes all of the objects in the Iterable from the game world
     * @param objs the Iterable to use
     */
    public void removeAll(Iterable<GameObject> objs) {
        manager.removeAll(objs);
    }

    /**
     * Removes all of the objects in the Collection of GameObjects from the
     * game world
     * @param objs the Collection to use
     */
    public void removeAll(Collection<GameObject> objs) {
        manager.removeAll(objs);
    }

    /**
     * Adds the specified GameObject to the game world
     * @param obj the object to add
     */
    public void add(GameObject obj) {
        manager.add(obj);
    }

    /**
     * Adds all the GameObjects in the Iterable to the game world
     * @param objs to Iterable to add
     */
    public void addAll(Iterable<GameObject> objs) {
        manager.addAll(objs);
    }

    /**
     * Adds all the GameObjects in the Collection to the game world
     * @param objs the Collection to add
     */
    public void addAll(Collection<GameObject> objs) {
        manager.addAll(objs);
    }

    /**
     * Gets a <i>read-only</i> copy of the collection of GameObjects in the
     * game world
     * @return the read-only collection
     */
    public Collection<GameObject> getAllObjects() {
        return manager.getAllObjects();
    }

    /**
     * Adds the specified amount to the game score
     * @param amount the amount to add
     */
    public void modifyScore(int amount) {
        manager.modifyScore(amount);
    }

    /**
     * Sets the score to the specified number
     * @param amount the number to set the score to
     */
    public void setScore(int amount) {
        manager.setScore(amount);
    }

    /**
     * Adds the specified number to the lives count
     * @param amount the amount to add
     */
    public void modifyLives(int amount) {
        manager.modifyLives(amount);
    }

    /**
     * Sets the current lives count to the given amount
     * @param amount the amount to set
     */
    public void setLives(int amount) {
        manager.setLives(amount);
    }
}
