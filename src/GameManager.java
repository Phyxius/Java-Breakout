

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.List;

/**
 * Created by Shea on 11/11/2014.
 */
public class GameManager {
    private final List<GameObject> objects = new ArrayList<>(),
            removedObjects = new ArrayList<>(),
            addedObjects = new ArrayList<>();
    private final Map<Integer,Boolean> keys;
    private boolean isDebug = false;
    private final GameArea gameArea;
    private int lives = 0, score = 0;

    public GameManager(GameArea gameArea) {
        keys = new HashMap<>();
        /*
        pre-populate the keys map with false for every key constant
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
    }

    public GameManager(GameArea gameArea, boolean isDebug) {
        this(gameArea);
        this.isDebug = isDebug;
    }

    public boolean isDebugMode() {
        return isDebug;
    }

    public void setDebugMode(boolean mode) {
        isDebug = mode;
    }

    public GameArea getGameArea() {
        return gameArea;
    }

    public void update() {
        processEntityListChanges();
        UpdateManager updateManager = new UpdateManager(this);
        for (GameObject o : objects) {
            o.update(updateManager);
        }
        processEntityListChanges();
        CollisionManager collisionManager = new CollisionManager(this);
        for (GameObject i : objects) {
            objects.stream().filter(j -> i != j && i.intersects(j))
                    .forEach(j -> i.onIntersect(j, collisionManager));
        }
        processEntityListChanges();
    }

    public void draw(Graphics2D graphics) {
        DrawingManager manager = new DrawingManager(this);
        for(GameObject o : objects) {
            o.draw(graphics, manager);
            if (isDebugMode()) {
                graphics.setStroke(new BasicStroke(2));
                graphics.setColor(Color.GREEN);
                graphics.draw(o.getBoundingArea());
                graphics.setColor(Color.PINK);
                graphics.draw(o.getBoundingRectangle());
            }
        }
    }

    public void remove(GameObject obj) {
        removedObjects.add(obj);
    }

    public void add(GameObject obj) {
        addedObjects.add(obj);
    }

    public void processEntityListChanges() {
        objects.removeAll(removedObjects);
        objects.addAll(addedObjects);
        removedObjects.clear();
        addedObjects.clear();
    }

    public void setKeyState(int key, boolean state) {
        keys.put(key, state);
    }

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

    public Collection<GameObject> getAllObjects() {
        return new ArrayList<>(objects);
    }

    public void modifyScore(int amount) {
        score += amount;
    }

    public void setScore(int amount) {
        score = amount;
    }

    public void modifyLives(int amount) {
        lives += amount;
    }

    public void setLives(int amount) {
        lives = amount;
    }
}
