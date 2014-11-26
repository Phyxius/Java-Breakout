/*
 * Ball.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Basic Ball class
 * Usage: Add to a GameManager
 */

package breakout.gameobjects;

import breakout.Util;
import breakout.managers.CollisionManager;
import breakout.managers.DrawingManager;
import breakout.managers.UpdateManager;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Ball extends GameObject implements MovableObject {
    private static final int MAX_STARTING_X_SPEED = 5,
        MAX_STARTING_Y_SPEED = 5, MIN_STARTING_X_SPEED = 3,
        MIN_STARTING_Y_SPEED = 3;
    private final Ellipse2D.Double ellipse;
    private final int radius;
    private int xSpeed = 0;
    private int ySpeed = 0;

    /**
     * Constructs a new Ball with given parameters
     * @param x        the center x value of the ball
     * @param y        the center y value of the ball
     * @param diameter the diameter of the ball
     */
    public Ball(int x, int y, int diameter) {
        super(null);
        ellipse = new Ellipse2D.Double(x + diameter / 2, y + diameter / 2,
                diameter, diameter);
        radius = diameter / 2;
    }

    /**
     * @return the X speed
     */
    public int getXSpeed() {
        return xSpeed;
    }

    /**
     * Set the current X speed to a given value
     * @param xSpeed the speed to set
     */
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * @return the Y speed
     */
    public int getYSpeed() {
        return ySpeed;
    }

    /**
     * Sets the  current Y speed to a given value
     * @param ySpeed the speed to set
     */
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * @return the current bounding Area
     */
    @Override
    public Area getBoundingArea() {
        return new Area(ellipse);
    }

    /**
     * Moves the ball forward and bounces off walls
     * @param updateManager the UpdateManager used to interact with the world
     */
    @Override
    public void update(UpdateManager updateManager) {
        int unclampedNewXPos = (int)ellipse.getX() + xSpeed;
        int unclampedNewYPos = (int)ellipse.getY() + ySpeed;
        int newXPos = Util.clamp(unclampedNewXPos, 0,
                (int) (updateManager.getGameAreaWidth() - ellipse.getWidth()));
        int newYPos = Util.clamp(unclampedNewYPos, 0,
                (int)(updateManager.getGameAreaHeight() - ellipse.getHeight()));
        if (newXPos != unclampedNewXPos) {
            xSpeed *= -1;
        }
        if (newYPos != unclampedNewYPos) {
            if (newYPos < unclampedNewYPos) { //bounced off bottom of game area
                updateManager.modifyLives(-1);
                newXPos = updateManager.getGameAreaWidth() / 2;
                newYPos = updateManager.getGameAreaHeight() / 2;
                xSpeed = Util.getRandomInRange(MIN_STARTING_X_SPEED,
                        MAX_STARTING_X_SPEED, true);
                ySpeed = Util.getRandomInRange(MIN_STARTING_Y_SPEED,
                        MAX_STARTING_Y_SPEED, false);
            }
            else {
                ySpeed *= -1;
            }
        }

        ellipse.x = newXPos;
        ellipse.y = newYPos;
    }

    /**
     * Reacts to collisions
     * @param other the GameObject that this one collided with
     * @param manager the CollisionManager used to interact with the game world
     */
    @Override
    public void onIntersect(GameObject other, CollisionManager manager) {
        if (other.getClass().equals(Paddle.class) && getYSpeed() > 0) {
            setYSpeed(-getYSpeed());
        }
        else if (other.getClass().equals(Brick.class)) {
            Brick otherBrick = (Brick)other;
            Rectangle otherRect = otherBrick.getBoundingRectangle();
            double brickCornerX = otherRect.getCenterX() - Math.copySign(
                    otherRect.getWidth() / 2, xSpeed);
            double brickCornerY = otherRect.getCenterY() - Math.copySign(
                    otherRect.getHeight() / 2, ySpeed);
            double ballCornerX = getBoundingRectangle().getCenterX() +
                    Math.copySign(radius, xSpeed);
            double ballCornerY = getBoundingRectangle().getCenterY() +
                    Math.copySign(radius, ySpeed);
            if (Math.abs(ballCornerX - brickCornerX) > Math.abs(ballCornerY -
                brickCornerY)) {
                ySpeed *= -1;
            }
            else {
                xSpeed *= -1;
            }
        }
    }

    /**
     * @return the current X value
     */
    @Override
    public int getX() {
        return (int)getBoundingRectangle().getCenterX();
    }

    /**
     * @return the current Y value
     */
    @Override
    public int getY() {
        return (int)getBoundingRectangle().getCenterY();
    }

    /**
     * Draws the Ball to the given canvas
     * @param g the Graphics2D canvas to use
     * @param manager the DrawingManager to use to interact with the game world
     */
    @Override
    public void draw(Graphics2D g, DrawingManager manager) {
        g.setColor(Color.GREEN);
        g.fill(getBoundingArea());
    }
}
