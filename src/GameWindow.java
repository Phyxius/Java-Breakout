import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Shea on 11/12/2014.
 */
public class GameWindow extends JFrame {
    JLabel scoreLabel = new JLabel("0 Points"),
        livesLabel = new JLabel(BreakoutGamePanel.STARTING_LIVES + " lives");
    BreakoutGamePanel gamePanel = new BreakoutGamePanel();
    public GameWindow() {
        super("Breakout");
        setLayout(new BorderLayout());
        add(gamePanel);
        add(new GameControlPanel(), BorderLayout.LINE_END);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class GameControlPanel extends JPanel {
        private final String PAUSE_TEXT = "Pause";
        private final String UNPAUSE_TEXT = "Unpause";
        private final String START_TEXT = "Start Game";
        JButton pauseButton = new JButton(START_TEXT);
        public GameControlPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(Box.createVerticalGlue());
            scoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(scoreLabel);
            livesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(livesLabel);
            pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            pauseButton.addActionListener(this::togglePause);
            add(pauseButton);
            setPreferredSize(new Dimension(200,100));
        }

        private void togglePause(ActionEvent e) {
            pauseButton.setText(
                    pauseButton.getText().equals(PAUSE_TEXT) ?
                            UNPAUSE_TEXT : PAUSE_TEXT
            );
            gamePanel.togglePause();
        }
    }
}
