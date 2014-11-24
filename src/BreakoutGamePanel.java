import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Shea on 11/14/2014.
 */
public class BreakoutGamePanel extends JPanel implements GameArea {
    public static final int STARTING_LIVES = 5;
    private GameManager manager;
    private Timer timer = new Timer((int)(1f/40*1000), this::update);
    private Level level, initialLevel;
    public BreakoutGamePanel(GameWindow.GameControlPanel panel, Level level,
                             boolean debug) {
        this.level = level;
        this.initialLevel = level;
        manager = new GameManager(this, panel::setScore,
                panel::setLives, debug);
        manager.setLives(STARTING_LIVES);
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        addDefaultObjects();
        addKeyListener(new KeyboardGameTranslator(manager));
        manager.addAll(level.createObjects());
    }

    private void addDefaultObjects() {
        manager.add(new Paddle(400, 590, 100, 10));
        Ball b = new Ball(400, 300, 20);
        b.setSpeed(new Point(1, 3));
        manager.add(b);
        manager.add(new UtilityObject());
    }

    private void resetLevel(boolean loadNext) {
        manager.removeAll();
        if (loadNext) {
            level = level.getNextLevelInSequence();
        }
        addDefaultObjects();
        manager.addAll(level.createObjects());
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

    private void showGameWonDialog() {
        JOptionPane.showMessageDialog(this,
                "Congratulations! You completed the level.\nReady for the next one?");
    }

    private void showGameLostDialog() {
        JOptionPane.showMessageDialog(this,
                "Oh dear. You appear to have lost.\nTry again?");
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

    private class UtilityObject extends GameObject {
        ToggleLatch debugLatch = new ToggleLatch();
        public UtilityObject() {
            super(new Rectangle(-10, -10, 1, 1));
        }

        @Override
        public void update(UpdateManager u) {
            if (u.getLives() < 1) {
                showGameLostDialog();
                u.setScore(0);
                u.setLives(STARTING_LIVES);
                level = initialLevel;
                resetLevel(false);
            }
            else if (u.getAllObjects().stream().noneMatch( //Check for game won
                    o -> o.getClass().isAssignableFrom(Brick.class))) {
                showGameWonDialog();
                resetLevel(true);
            }
            else if (debugLatch.update(u.getKeyState(KeyEvent.VK_CONTROL) &&
                    u.getKeyState(KeyEvent.VK_D))) {
                manager.setDebugMode(!manager.isDebugMode());
            }
        }
    }
}
