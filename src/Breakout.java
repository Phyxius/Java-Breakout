import javax.swing.*;
import java.io.IOException;

/**
 * Created by Shea on 11/14/2014.
 */
public class Breakout {
    private static String[] argsArray;
    public static void main(String[] args) {
        argsArray = args;
        SwingUtilities.invokeLater(Breakout::createAndShowGui);
    }

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
                (argsArray.length > 2) && argsArray[2].toLowerCase().equals("debug")
        ).setVisible(true);
    }
}
