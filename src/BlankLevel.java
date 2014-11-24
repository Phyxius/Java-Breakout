import java.util.Collections;

/**
 * Created by Phyxius on 11/23/2014.
 */
public class BlankLevel implements Level {
    @Override
    public Iterable<GameObject> createObjects() {
        return Collections.emptyList();
    }
}
