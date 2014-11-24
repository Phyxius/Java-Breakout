/**
 * Created by Phyxius on 11/22/2014.
 */
public interface Level {
    Iterable<GameObject> createObjects();
    default Level getNextLevelInSequence() {
        return this;
    }
}
