package breakout;

/**
 * Created by Phyxius on 11/23/2014.
 */
public class ToggleLatch {
    private boolean lastState = false;

    public boolean update(boolean state) {
        boolean ret = !lastState & state;
        lastState = state;
        return ret;
    }
}
