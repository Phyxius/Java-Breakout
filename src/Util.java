/**
 * Created by Phyxius on 11/15/2014.
 */
public final class Util {
    public static int clamp(int val, int min, int max) {
        return Math.max(Math.min(val, max), min);
    }
}
