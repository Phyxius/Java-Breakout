import java.util.Collection;

/**
 * Created by Phyxius on 11/19/2014.
 */
public class MutableStateEventManager extends EventManager {
    public MutableStateEventManager(GameManager manager) {
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

    public void modifyScore(int amount) {
        manager.modifyScore(amount);
    }

    public void setScore(int amount) {
        manager.setScore(amount);
    }

    public void modifyLives(int amount) {
        manager.modifyLives(amount);
    }

    public void setLives(int amount) {
        manager.setLives(amount);
    }
}
