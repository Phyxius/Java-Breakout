import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Shea on 11/14/2014.
 */
public class BreakoutGamePanel extends JPanel implements GameArea {
    public static final int STARTING_LIVES = 5;
    private GameManager manager = new GameManager(this, true);
    private Timer timer = new Timer((int)(1f/40*1000), this::update);
    public BreakoutGamePanel() {
        setPreferredSize(new Dimension(800, 600));
        manager.add(new Brick(100, 100, 10, 20));
        manager.add(new Ball(300, 150, 25));
        addKeyListener(new KeyboardGameTranslator(manager));
        timer.start();
    }

    public void togglePause() {
        if (timer.isRunning()) {
            timer.stop();
        }
        else {
            timer.start();
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
}
