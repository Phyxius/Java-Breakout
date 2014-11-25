/*
 * GameManager.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Main game manager class. Contains the list of active GameObjects,
 * and is in charge of calling their update, draw, and onIntersect methods.
 * Also contains the scoring and lives code, with callbacks for updating
 * external data.
 * Usage: Create with required parameters, then call update() and draw() at
 * fixed intervals
 */

package breakout.managers;

import breakout.GameArea;
import breakout.gameobjects.GameObject;
import breakout.gameobjects.Object2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.List;
import java.util.function.IntConsumer;

public class GameManager {
    private final List<GameObject> objects = new ArrayList<>(),
            removedObjects = new ArrayList<>(),
            addedObjects = new ArrayList<>();
    private final Map<Integer,Boolean> keys;
    private boolean isDebug = false;
    private final GameArea gameArea;
    private int lives = 0, score = 0;
    private final IntConsumer scoreChangedCallback, livesChangedCallback;

    /**
     * Constructs a new GameManager using the specified gameArea and callbacks
     * @param gameArea The GameArea used in the game
     * @param scoreChangedCallback The callback used to update an external score
     *                             display
     * @param livesChangedCallback the callback used to update an external lives
     *                             display
     */
    public GameManager(GameArea gameArea, IntConsumer scoreChangedCallback,
                       IntConsumer livesChangedCallback) {
        keys = new HashMap<>();
        /*
        pre-populate the keys map with false for every key constant;
        saves from having to check for map key existence later
        */
        for (Field f : KeyEvent.class.getDeclaredFields()) {
            try {
                if (Modifier.isStatic(f.getModifiers()) &&
                        f.getType() == int.class) {
                    keys.put(f.getInt(null), false);
                }
            }
            catch(IllegalAccessException ignore) {
                //Don't do anything, we only care about public fields anyway
            }
        }
        this.gameArea = gameArea;
        this.scoreChangedCallback = scoreChangedCallback;
        this.livesChangedCallback = livesChangedCallback;
    }

    /**
     * Constructs a new GameManager using the specified gameArea and callbacks
     * @param gameArea The GameArea used in the game
     * @param scoreChangedCallback The callback used to update an external score
     *                             display
     * @param livesChangedCallback the callback used to update an external lives
     *                             display
     * @param isDebug controls whether the GameManager will be created with
     *                debug mode on (true) or off (false)
     */
    public GameManager(GameArea gameArea, IntConsumer scoreChangedCallback,
                       IntConsumer livesChangedCallback, boolean isDebug) {
        this(gameArea, scoreChangedCallback, livesChangedCallback);
        this.isDebug = isDebug;
    }

    /**
     * @return whether or not debug mode is enabled
     */
    public boolean isDebugMode() {
        return isDebug;
    }

    /**
     * Sets debug mode to on (true) or off (false)
     * @param mode the debug mode to use
     */
    public void setDebugMode(boolean mode) {
        isDebug = mode;
    }

    /**
     * @return the GameArea used in the game
     */
    public GameArea getGameArea() {
        return gameArea;
    }

    /**
     * Advances the game world one step, calling each object's update() and
     * then (if applicable) onIntersect() methods
     */
    public void update() {
        processGameObjectListChanges();
        UpdateManager updateManager = new UpdateManager(this);
        for (GameObject o : objects) {
            o.update(updateManager);
        }
        processGameObjectListChanges();
        CollisionManager collisionManager = new CollisionManager(this);
        for (GameObject i : objects) {
            objects.stream().filter(j -> i != j && i.intersects(j))
                    .forEach(j -> i.onIntersect(j, collisionManager));
        }
        processGameObjectListChanges();
    }

    /**
     * Draws the game world to the specified Graphics2D object
     * @param graphics the graphics object to draw to
     */
    public void draw(Graphics2D graphics) {
        DrawingManager manager = new DrawingManager(this);
        for(GameObject o : objects) {
            o.draw(graphics, manager);
        }
        if (isDebugMode()) {
            for (GameObject o : objects) {
                graphics.setStroke(new BasicStroke(2));
                graphics.setColor(Color.GREEN);
                graphics.draw(o.getBoundingArea());
                graphics.setColor(Color.PINK);
                graphics.draw(o.getBoundingRectangle());
            }
        }
    }

    /**
     * Removes the specified GameObject from the game world at the next update
     * @param obj the object to remove
     */
    public void remove(GameObject obj) {
        removedObjects.add(obj);
    }

    /**
     * Adds the specified GameObject to the game world at the next update
     * @param obj the object to add
     */
    public void add(GameObject obj) {
        addedObjects.add(obj);
    }

    /**
     * Adds all the GameObjects in the Iterable to the game world at the next
     * update
     * @param objs the Iterable whose objects should be added
     */
    public void addAll(Iterable<GameObject> objs) {
        objs.forEach(this::add);
    }

    /**
     * Adds all the GameObjects in the Collection to the game world at the next
     * update
     * Collection.addAll is slightly more efficient than Iterable.forEach, so
     * this method is included despite its slight redundance.
     * @param objs the Collection whose objects should be added
     */
    public void addAll(Collection<GameObject> objs) {
        addedObjects.addAll(objs);
    }

    /**
     * Removes all the GameObjects in the Iterable from the game world at the
     * next update.
     * @param objs the Iterable whose objects are to be removed
     */
    public void removeAll(Iterable<GameObject> objs) {
        objs.forEach(removedObjects::add);
    }

    /**
     * Removes all the GameObjects in the Collection from the game world at the
     * next update.
     * Collection.removeAll is slightly more efficient than Iterable.forEach,
     * so this method is included despite its slight redundance.
     * @param objs the Collection of GameObjects to remove
     */
    public void removeAll(Collection<GameObject> objs) {
        removedObjects.addAll(objs);
    }

    /**
     * Removes <b>all</b> the GameObjects from the world at the next update
     */
    public void removeAll() {
        removedObjects.addAll(objects);
    }

    /**
     * Processes changes (additions/removals) to the list of GameObjects
     */
    public void processGameObjectListChanges() {
        objects.removeAll(removedObjects);
        objects.addAll(addedObjects);
        removedObjects.clear();
        addedObjects.clear();
    }

    /**
     * Sets the given key to the given state
     * @param key the key to set, given by a VK_ constant in KeyEvent
     * @param state the state of the key: pressed (true) or not (false)
     */
    public void setKeyState(int key, boolean state) {
        keys.put(key, state);
    }

    /**
     * Returns the state of the given key, given by a VK_ constant in KeyEvent
     * @param key the key whose state should be checked
     * @return the state of the key: pressed (true) or not (false)
     */
    public boolean getKeyState(int key) {
        return keys.get(key);
    }

    /**
     * Returns whether or not the object is out of the game area based on its
     * bounding rectangle
     * @param obj the object to check
     * @return true if the object is out of bounds, false otherwise
     */
    public boolean isOutOfBounds(Object2D obj) {
        return !gameArea.getAsRectangle().contains(obj.getBoundingRectangle());
    }

    /**
     * @return a <b>read-only</b> Collection containing all the GameObjects in
     * the world. Does <i>not</i> necessarily reflect current additions or
     * removals
     */
    public Collection<GameObject> getAllObjects() {
        return Collections.unmodifiableCollection(objects);
    }

    /**
     * Adds the specified amount to the current score, and calls the
     * scoreChangedCallback with the updated amount
     * @param amount the amount to add
     */
    public void modifyScore(int amount) {
        score += amount;
        scoreChangedCallback.accept(score);
    }

    /**
     * Sets the score to the specified amount, and calls the
     * scoreChangedCallback with the updated amount
     * @param amount the amount to set the score to
     */
    public void setScore(int amount) {
        score = amount;
        scoreChangedCallback.accept(score);
    }

    /**
     * Adds the specified amount to the lives, and calls the
     * livesChangedCallback with the updated amount
     * @param amount the amount to add to the current life count
     */
    public void modifyLives(int amount) {
        lives += amount;
        livesChangedCallback.accept(lives);
    }

    /**
     * Sets the life count to the specified amount, and calls the
     * livesChangedCallback with the new amount
     * @param amount the amount to set the score to
     */
    public void setLives(int amount) {
        lives = amount;
        livesChangedCallback.accept(lives);
    }

    /**
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the current life count
     */
    public int getLives() {
        return lives;
    }
}
