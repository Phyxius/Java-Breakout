/*
 * BreakoutGamePanel.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Draws the game to the window
 * Usage: Used by BreakoutGameWindow
 */

package breakout.ui;

import breakout.GameArea;
import breakout.Util;
import breakout.levels.Level;
import breakout.gameobjects.Ball;
import breakout.gameobjects.Brick;
import breakout.gameobjects.GameObject;
import breakout.gameobjects.Paddle;
import breakout.managers.GameManager;
import breakout.managers.UpdateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class BreakoutGamePanel extends JPanel implements GameArea {
    public static final int STARTING_LIVES = 5;
    private final GameManager manager;
    private final Timer timer = new Timer((int)(1f/40*1000), this::update);
    private Level level;
    private final Level initialLevel;

    /**
     * Constructs a new BreakoutGamePanel with the given ControlPanel, Level,
     * and debug mode
     * @param panel the ControlPanel used to display score and to control
     *              pausing
     * @param level the Level the game will start on
     * @param debug whether or not to start with debug mode on (true) or off
     *              (false)
     */
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

    /**
     * Adds the 'default' objects to the current game world
     * Includes the Ball, the Paddle, and the UtilityObject
     */
    private void addDefaultObjects() {
        manager.add(new Paddle(400, 590, 100, 10));
        Ball b = new Ball(400, 300, 20);
        b.setSpeed(new Point(1, 3));
        manager.add(b);
        manager.add(new UtilityObject());
    }

    /**
     * Optionally advances the level, then resets the game world to the default
     * state, including 'default' objects.
     * @param loadNext whether or not to advance the level before the reset
     */
    private void resetLevel(boolean loadNext) {
        manager.removeAll();
        if (loadNext) {
            level = level.getNextLevelInSequence();
        }
        addDefaultObjects();
        manager.addAll(level.createObjects());
    }

    /**
     * Toggles whether or not the game is paused
     */
    public void togglePause() {
        if (timer.isRunning()) {
            timer.stop();
        }
        else {
            timer.start();
            requestFocus();
        }
    }

    /**
     * Shows the 'You won' dialog, blocking until it is closed.
     */
    private void showGameWonDialog() {
        JOptionPane.showMessageDialog(this,
                "Congratulations! You completed the level.\n" +
                        "Ready for the next one?");
    }

    /**
     * Shows the 'You lose' dialog, blocking until it is closed
     */
    private void showGameLostDialog() {
        JOptionPane.showMessageDialog(this,
                "Oh dear. You appear to have lost.\nTry again?");
    }

    /**
     * Draws the game world to the Graphics canvas
     * @param g the canvas to draw on
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        manager.draw((Graphics2D)g);
    }

    /**
     * Updates the game world, then repaints the panel
     * @param e Ignored
     */
    private void update(ActionEvent e) {
        manager.update();
        repaint();
    }

    /**
     * A Utility class that detects game winning and losing, and toggles debug
     * mode.
     */
    private class UtilityObject extends GameObject {
        final Util.ToggleLatch debugLatch = new Util.ToggleLatch();

        /**
         * Constructs a new UtilityObject
         */
        public UtilityObject() {
            super(new Rectangle(-10, -10, 1, 1));
        }

        /**
         * Checks for and responds to winning and losing conditions, as well as
         * toggling debug mode when appropriate
         * @param u the UpdateManager to use to interact with the game world
         */
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
