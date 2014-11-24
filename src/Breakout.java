import java.io.IOException;

/**
 * Created by Shea on 11/14/2014.
 */
public class Breakout {
    public static void main(String[] args) {
        //TODO: Do threading properly
        Level level;
        try {
            switch (args[0]) {
                case "fs":
                    level = new FileSystemBasicFileLevel(args[1]);
                    break;
                case "jar":
                    level = new JarFileBasicFileLevel(args[1]);
                    break;
                default:
                    throw new IOException();
            }
        }
        catch(IOException|ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "Loading level failed, falling back to internal level...");
            level = new BasicLevel(5, 13, 20, 20, 40, 20, 20, 20, 14);
        }
        new GameWindow(level,
                (args.length > 2) && args[2].toLowerCase().equals("debug")
        ).setVisible(true);
    }
}
