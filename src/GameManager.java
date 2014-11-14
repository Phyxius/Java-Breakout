

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public GameManager(GameArea gameArea) {
        keys = new HashMap<>();
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
        processRemovals();
        processAdditions();
        UpdateManager manager = new UpdateManager(this);
        for (GameObject o : objects) {
            o.update(manager);
        }
        processRemovals();
        processAdditions();
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

    private void processRemovals()
    {
        objects.removeAll(removedObjects);
        removedObjects.clear();
    }

    public void add(GameObject obj) {
        addedObjects.add(obj);
    }

    public void processAdditions() {
        objects.addAll(addedObjects);
        addedObjects.clear();
    }

    public void setKey(int key, boolean state) {
        keys.put(key, state);
    }

    public boolean getKey(int key) {
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

}
