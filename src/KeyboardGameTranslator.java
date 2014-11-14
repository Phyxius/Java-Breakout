import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Phyxius on 11/14/2014.
 */
public class KeyboardGameTranslator extends KeyAdapter {
    private final GameManager gameManager;

    public KeyboardGameTranslator(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameManager.setKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gameManager.setKey(e.getKeyCode(), false);
    }
}
