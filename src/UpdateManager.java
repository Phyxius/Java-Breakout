import java.awt.*;

/**
* Created by Shea on 11/11/2014.
*/
public class UpdateManager extends EventManager {
    public UpdateManager(GameManager manager) {
        super(manager);
    }

    public void remove(GameObject obj) {
        manager.remove(obj);
    }

    public void add(GameObject obj) {
        manager.add(obj);
    }

}
