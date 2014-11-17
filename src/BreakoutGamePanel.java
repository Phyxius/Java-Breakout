import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Shea on 11/14/2014.
 */
public class BreakoutGamePanel extends JPanel implements GameArea {
    public static final int STARTING_LIVES = 5;
    private final GameWindow.GameControlPanel controlPanel;
    private int lives = STARTING_LIVES, score = 0;
    private GameManager manager = new GameManager(this, true);
    private Timer timer = new Timer((int)(1f/40*1000), this::update);
    public BreakoutGamePanel(GameWindow.GameControlPanel panel) {
        controlPanel = panel;
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        manager.add(new Paddle(400, 590, 100, 10));
        Ball b = new Ball(400, 300, 20, this::modifyLives, this::modifyScore);
        b.setSpeed(new Point(1, 3));
        manager.add(b);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                manager.add(new Brick(i*80, j * 20,40, 20));
            }
        }
        addKeyListener(new KeyboardGameTranslator(manager));
    }

    public void togglePause() {
        if (timer.isRunning()) {
            timer.stop();
        }
        else {
            timer.start();
            requestFocus();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        manager.draw((Graphics2D)g);
    }

    private void update(ActionEvent e) {
        manager.update();
        repaint();
    }

    private void modifyLives(int amount) {
        lives += amount;
        controlPanel.setLives(lives);
    }

    private void modifyScore(int amount) {
        score += amount;
        controlPanel.setScore(score);
    }
}
