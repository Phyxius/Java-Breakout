/*
 * Util.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Utility methods for use in other classes
 */

package breakout;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public final class Util {
    //Premade RNG for general usage
    public static final Random RANDOM = new Random();

    /**
     * Clamps a given value to be at most a given maximum, and at least a given
     * minimum
     * @param val the value to clamp
     * @param min the minimum value of the return
     * @param max the maximum value of the return
     * @return the clamped value
     */
    public static int clamp(int val, int min, int max) {
        return Math.max(Math.min(val, max), min);
    }

    /**
     * Gets a random number in a given range, optionally with the chance that
     * the number will be multiplied by -1
     * The chance is exactly 50/50 if so.
     * @param min the minimum value to return
     * @param max the maximum value to return
     * @param negativeChance whether or not to include the possibility of a
     *                       sign-flipped result
     * @return the number that is generated
     */
    public static int getRandomInRange(int min, int max,
                                       boolean negativeChance) {
        return (negativeChance && RANDOM.nextBoolean() ? -1 : 1) *
                (RANDOM.nextInt(max - min + 1) + min);
    }

    /**
     * Gets the closest int to a target value from any number of integers
     * @param target the target integer to compare to
     * @param integer one integer to compare to
     * @param integers the rest of the integers to compare to
     * @return the closest integer to the target
     */
    public static int getClosestInt(int target, int integer, int... integers) {
        return Stream.concat(Arrays.stream(integers).boxed(),Stream.of(
                integer)).sorted((a, b) -> Integer.compareUnsigned(target - a,
                target - b)).findFirst().get();
    }

    /**
     * Returns an InputStream to a file in the Jar
     * @param path the path to the file
     * @return an InputStream from the file
     */
    public static InputStream getFileFromJar(String path) {
        return Util.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * Returns the String contained in a given file in the Jar
     * @param path the path to the file
     * @return the String contained in the file
     */
    public static String getStringFromJar(String path) {
        try(Scanner s = new Scanner(getFileFromJar(path))) {
            return s.useDelimiter("\\A").next();
        }
    }

    /**
     * Boolean rising edge triggered latch class designed to be used for
     * key press style events
     */
    public static class ToggleLatch {
        private boolean lastState = false;

        /**
         * Updates the state of the latch
         * @param state the current state
         * @return true if the new state is true and the previous state is false,
         * false otherwise
         */
        public boolean update(boolean state) {
            boolean ret = !lastState & state;
            lastState = state;
            return ret;
        }
    }
}
