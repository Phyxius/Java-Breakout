/*
 * KeyboardGameTranslator.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Translates KeyEvents to a format understood by GameManager instances
 * Usage: Add as a KeyListener to the game's panel
 */

package breakout.ui;

import breakout.managers.GameManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardGameTranslator extends KeyAdapter {
    private final GameManager gameManager;

    /**
     * Creates a new KeyboardGameTranslator referencing the specified
     * GameManager
     * @param gameManager The GameManager to update with key presses
     */
    public KeyboardGameTranslator(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Called when a key is pressed
     * @param e the KeyEvent containing the key's information
     */
    @Override
    public void keyPressed(KeyEvent e) {
        gameManager.setKeyState(e.getKeyCode(), true);
    }

    /**
     * Called when a key is released
     * @param e the KeyEvent containing the key's information
     */
    @Override
    public void keyReleased(KeyEvent e) {
        gameManager.setKeyState(e.getKeyCode(), false);
    }
}
