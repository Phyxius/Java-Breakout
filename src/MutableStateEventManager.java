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

    public void removeAll(Iterable<GameObject> objs) {
        manager.removeAll(objs);
    }

    public void removeAll(Collection<GameObject> objs) {
        manager.removeAll(objs);
    }

    public void add(GameObject obj) {
        manager.add(obj);
    }

    public void addAll(Iterable<GameObject> objs) {
        manager.addAll(objs);
    }

    public void addAll(Collection<GameObject> objs) {
        manager.addAll(objs);
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
