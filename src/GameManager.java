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
    private final GameArea gameArea;

    public GameManager(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    public GameArea getGameArea() {
        return gameArea;
    }

    public void update() {
        processRemovals();
        processAdditions();
        for (GameObject o : objects) {
            o.update();
        }
        processRemovals();
        processAdditions();
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
