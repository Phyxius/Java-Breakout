/*
 * GameWindow.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: The main window for the Breakout game
 * Usage: Construct and show
 */

package breakout.ui;

import breakout.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    private final JLabel scoreLabel = new JLabel("0 Points");
    private final JLabel livesLabel = new JLabel(
            BreakoutGamePanel.STARTING_LIVES + " lives");
    private final BreakoutGamePanel gamePanel;

    /**
     * Constructs a new GameWindow with the given Level and the given debug mode
     * state
     * @param level the Level to use
     * @param debugMode the debug state to use
     */
    public GameWindow(Level level, boolean debugMode) {
        super("Breakout");
        setLayout(new BorderLayout());
        GameControlPanel controlPanel = new GameControlPanel();
        add(controlPanel, BorderLayout.LINE_END);
        gamePanel = new BreakoutGamePanel(controlPanel, level, debugMode);
        add(gamePanel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The right hand side of the game window
     * Contains a score display and a start/pause/unpause button
     */
    public class GameControlPanel extends JPanel {
        private final String PAUSE_TEXT = "Pause";
        private final String UNPAUSE_TEXT = "Unpause";
        private final String START_TEXT = "Start Game";
        private final JButton pauseButton = new JButton(START_TEXT);

        /**
         * Constructs a new GameControlPanel
         */
        public GameControlPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(Box.createVerticalGlue());
            add(scoreLabel);
            add(livesLabel);
            livesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            pauseButton.addActionListener(this::togglePause);
            add(pauseButton);
            setPreferredSize(new Dimension(200,100));
        }

        /**
         * Toggles whether or not the game should be paused
         * @param e
         */
        private void togglePause(ActionEvent e) {
            pauseButton.setText(
                    pauseButton.getText().equals(PAUSE_TEXT) ?
                            UNPAUSE_TEXT : PAUSE_TEXT
            );
            gamePanel.togglePause();
        }

        /**
         * Updates the lives display with the given lives amount
         * @param lives the lives amount to use
         */
        public void setLives(int lives) {
            livesLabel.setText(lives + " lives");
        }

        /**
         * Updates the score display to the given amount
         * @param score the score amount to use
         */
        public void setScore(int score) {
            scoreLabel.setText(score + " points");
        }
    }
}
