import java.util.Arrays;
import java.util.Random;

/**
 * Created by Phyxius on 11/15/2014.
 */
public final class Util {
    public static final Random RANDOM = new Random();

    public static int clamp(int val, int min, int max) {
        return Math.max(Math.min(val, max), min);
    }

    public static int getRandomInRange(int min, int max,
                                       boolean negativeChance) {
        return (negativeChance && RANDOM.nextBoolean() ? -1 : 1) *
                (RANDOM.nextInt(max - min + 1) + min);
    }

    public static int getClosestInt(int target, int... integers) {
        return Arrays.stream(integers).boxed().sorted(
                (a, b) -> Integer.compareUnsigned(target - a, target - b))
                .findFirst().get();
    }
}
