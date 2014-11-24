/**
 * Created by Shea on 11/14/2014.
 */
public class Breakout {
    public static void main(String[] args) {
        //TODO: Do threading properly
        new GameWindow(new BasicLevel(5, 13, 20, 20, 40, 20, 20, 20, 14),
                (args.length > 0) && args[0].toLowerCase().equals("debug")
        ).setVisible(true);
    }
}
