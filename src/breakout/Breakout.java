/*
 * Breakout.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: The main class for Breakout
 * Usage: Run on the command line. Accepts no arguments for default level,
 * or <fs/jar> <path> to load a level from the filesystem (fs) or Jar (jar),
 * or <fs/jar> <path> <debug> to load the level in debug mode.
 * Debug mode can be toggled with Control-D when the game is unpaused.
 * Note: This whole project uses Java 8 features.
 */

package breakout;

import breakout.levels.BasicLevel;
import breakout.levels.FileSystemBasicFileLevel;
import breakout.levels.JarFileBasicFileLevel;
import breakout.levels.Level;
import breakout.ui.GameWindow;

import javax.swing.*;
import java.io.IOException;

public class Breakout {
    private static String[] argsArray;

    /**
     * Main method. Loads the level, falling back to a default one if necessary,
     * then creates and shows the game GUI on another thread.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        argsArray = args;
        SwingUtilities.invokeLater(Breakout::createAndShowGui);
    }

    /**
     * Creates and shows the game GUI. This includes loading the level or
     * falling back to a default one.
     */
    private static void createAndShowGui() {
        Level level;
        try {
            switch (argsArray[0]) {
                case "fs":
                    level = new FileSystemBasicFileLevel(argsArray[1]);
                    break;
                case "jar":
                    level = new JarFileBasicFileLevel(argsArray[1]);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        catch(IOException|ArrayIndexOutOfBoundsException|
                IllegalArgumentException e) {
            System.out.println(
                    "Loading level failed, falling back to internal level...");
            level = new BasicLevel(5, 13, 20, 20, 40, 20, 20, 20, 14);
        }
        new GameWindow(level,
                (argsArray.length > 2) &&
                        argsArray[2].toLowerCase().equals("debug")
        ).setVisible(true);
    }
}
