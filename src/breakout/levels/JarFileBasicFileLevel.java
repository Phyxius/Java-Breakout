/*
 * JarFileBasicFileLevel.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Retrieves a basic Level from the Jar file
 * Usage: Instantiate and pass where appropriate
 */

package breakout.levels;

import breakout.Util;

public class JarFileBasicFileLevel extends BasicFileLevel {
    /**
     * Constructs a new Level using a given path, relative to the root of the
     * Jar file
     * @param filePath the path to use
     */
    public JarFileBasicFileLevel(String filePath) {
        super(Util.getStringFromJar(filePath));
    }
}
