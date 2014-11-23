/**
 * Created by Shea on 11/14/2014.
 */
public class Breakout {
    public static void main(String[] args) {
        //TODO: Do threading properly
        new GameWindow(
                args.length > 0 && args[0].toLowerCase().equals("debug")
        ).setVisible(true);
    }
}
