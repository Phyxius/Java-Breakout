import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shea on 11/11/2014.
 */
public class GameManager {
    private final List<GameObject> objects = new ArrayList<>(),
            removedObjects = new ArrayList<>(),
            addedObjects = new ArrayList<>();
    private boolean isDebug = false;
    private final GameArea gameArea;

    public GameManager(GameArea gameArea) {
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

}
