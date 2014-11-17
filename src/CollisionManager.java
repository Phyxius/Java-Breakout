import java.util.Collection;

/**
 * Created by Phyxius on 11/15/2014.
 */
public class CollisionManager extends EventManager {
    public CollisionManager(GameManager manager) {
        super(manager);
    }

    public void remove(GameObject obj) {
        manager.remove(obj);
    }

    public void add(GameObject obj) {
        manager.add(obj);
    }

    public Collection<GameObject> getAllObjects() {
        return manager.getAllObjects();
    }
}
