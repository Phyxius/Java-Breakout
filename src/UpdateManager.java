/**
* Created by Shea on 11/11/2014.
*/
public class UpdateManager extends MutableStateEventManager {
    public UpdateManager(GameManager manager) {
        super(manager);
    }

    public boolean getKeyState(int key) {
        return manager.getKeyState(key);
    }
}
