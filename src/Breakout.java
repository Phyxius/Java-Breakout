import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Shea on 11/14/2014.
 */
public class Breakout {
    public static void main(String[] args) {
        //TODO: Do threading properly
        Level level;
        try {
            level = new FileSystemBasicFileLevel(Paths.get(
                    "levels", "examples", "diamond.csv"));
        }
        catch(IOException e) {
            level = new BasicLevel(5, 13, 20, 20, 40, 20, 20, 20, 14);
        }
        new GameWindow(level,
                (args.length > 0) && args[0].toLowerCase().equals("debug")
        ).setVisible(true);
    }
}
