/*
 * Paddle.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Paddle for breakout
 * Usage: Add to a GameManager
 */

package breakout.gameobjects;

import breakout.managers.DrawingManager;
import breakout.managers.UpdateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

public class Paddle extends GameObject {
    private final Rectangle rect;
    private final int moveSpeed = 10;
    /**
     * Constructs a new Paddle with the given parameters
     * @param x the leftmost x value of the paddle
     * @param y the leftmost y value of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     */
    public Paddle(int x, int y, int width, int height) {
        rect = new Rectangle(x-width/2, y, width, height);
    }

    /**
     * @return the current bounding Area
     */
    @Override
    public Area getBoundingArea() {
        return new Area(rect);
    }

    /**
     * Moves the paddle according to user input
     * @param updateManager the UpdateManager used to interact with the game
     *                      world
     */
    @Override
    public void update(UpdateManager updateManager) {
        if (updateManager.getKeyState(KeyEvent.VK_RIGHT)) {
            rect.translate(moveSpeed, 0);
        }
        if (updateManager.getKeyState(KeyEvent.VK_LEFT)) {
            rect.translate(-moveSpeed, 0);
        }
        if (rect.getMinX() < 0) {
            rect.setLocation(0, getY());
        }
        else if (rect.getMaxX() > updateManager.getGameAreaWidth()) {
            rect.setLocation(updateManager.getGameAreaWidth() - getWidth(),
                    (int)rect.getY());
        }
    }

    /**
     * Draws the paddle to the game world
     * @param g the Graphics2D canvas to use
     * @param manager the DrawingManager to use to interact with the game world
     */
    @Override
    public void draw(Graphics2D g, DrawingManager manager) {
        g.setColor(Color.RED);
        g.fill(getBoundingArea());
    }
}
