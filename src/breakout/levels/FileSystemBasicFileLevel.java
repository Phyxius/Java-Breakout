/*
 * FileSystemBasicFileLevel.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Parses a basic Level from the File System
 * Usage: Pass a pre-loaded String
 */

package breakout.levels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileSystemBasicFileLevel extends BasicFileLevel {
    /**
     * Constructs a level from the given Path
     * @param filePath the path to use
     * @throws IOException
     */
    public FileSystemBasicFileLevel(Path filePath) throws IOException {
        super(Files.readAllLines(filePath).stream().collect(
                Collectors.joining("\n")));
    }

    /**
     * Constructs a level from the path represented by a given String
     * @param filePath the path of the file
     * @throws IOException
     */
    public FileSystemBasicFileLevel(String filePath) throws IOException{
        this(Paths.get(filePath));
    }
}
