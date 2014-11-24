package breakout;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

    public static InputStream getFileFromJar(String path) {
        return Util.class.getClassLoader().getResourceAsStream(path);
    }

    public static String getStringFromJar(String path) {
        try(Scanner s = new Scanner(getFileFromJar(path))) {
            return s.useDelimiter("\\A").next();
        }
    }
}
