/*
 * Brick.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Bare-bones Brick object for Breakout
 * Usage: Add to a GameArea
 */

package breakout.gameobjects;

import breakout.managers.CollisionManager;
import breakout.managers.DrawingManager;
import breakout.managers.UpdateManager;

import java.awt.*;

public class Brick extends GameObject implements MovableObject {
    private static final float[] HIT_NUMBER_HUES = {
            0, 60f/360, 180f/360, 240f/360, 300f/360, 120f/360,
    };
    private final int RECTANGLE_SPACING = 2;
    private int hits;
    private final Rectangle rect;
    private final int value;
    private int xSpeed = 0, ySpeed = 0;
    /**
     * Constructs a new Brick with given parameters
     * @param x the leftmost x of the paddle
     * @param y the uppermost y of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     * @param hits the number of hits to destroy the brick, max 20
     * @param value the score value of the brick
     */
    public Brick(int x, int y, int width, int height, int hits, int value) {
        if (hits > 20) {
            throw new IllegalArgumentException("Hits cannot exceed 20");
        }
        this.hits = hits;
        this.value = value;
        rect = new Rectangle(x, y, width, height);
        setBoundingArea(rect);
    }
    /**
     * Constructs a new Brick with given parameters
     * @param x the leftmost x of the paddle
     * @param y the uppermost y of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     * @param hits the number of hits to destroy the brick, max 20
     */
    public Brick(int x, int y, int width, int height, int hits) {
        this(x, y, width, height, hits, hits * 100);
    }

    /**
     * Constructs a new Brick with given parameters
     * @param x the leftmost x of the paddle
     * @param y the uppermost y of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     */
    public Brick(int x, int y, int width, int height) {
        this(x, y, width, height, 1);
    }

    /**
     * @return the X speed
     */
    public int getXSpeed() {
        return xSpeed;
    }

    /**
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
     * @param ySpeed the speed to set
     */
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * Moves the brick its speed (default 0)
     * @param updateManager unused
     */
    @Override
    public void update(UpdateManager updateManager) {
        rect.translate(getXSpeed(), getYSpeed());
        setBoundingArea(rect);
    }

    /**
     * Decrements the hit counter, destroying the brick if it is zero.
     * Negative hit counters will not be changed or destroyed.
     * 10 points are awarded for a decrement, or maxhits*100 if it is destroyed
     * Invincible bricks award their worth every time
     * @param other the GameObject that this one collided with
     * @param manager the CollisionManager used to interact with the game world
     */
    @Override
    public void onIntersect(GameObject other, CollisionManager manager) {
        if (other.getClass() == Ball.class) {
            if (hits > 0) {
                hits--;
                if (hits < 1) {
                    manager.remove(this);
                    manager.modifyScore(getWorth());
                }
                else {
                    manager.modifyScore(10);
                }
            }
            else {
                manager.modifyScore(getWorth());
            }
        }
    }

    /**
     * Draws the brick to the world. Bricks have 6 possible colors, and are
     * drawn in layers, spaced according to RECTANGLE_SPACING. The outermost
     * layer has 100% saturation, the next with 80%, and so on.
     * All layers except the innermost are drawn with the last color.
     * The innermost layer is indexed based on the number of hits.
     * Invincible bricks are drawn as solid gray.
     * @param g the Graphics2D canvas to use
     * @param manager the DrawingManager to use to interact with the game world
     */
    @Override
    public void draw(Graphics2D g, DrawingManager manager) {
        if (hits < 0) {
            g.setColor(Color.GRAY);
            g.fill(getBoundingArea());
            return;
        }
        int level = 0;
        for(; level < hits / HIT_NUMBER_HUES.length; level++) {
            g.setColor(Color.getHSBColor(
                    HIT_NUMBER_HUES[HIT_NUMBER_HUES.length - 1], 1,
                    1 - level * .2f));
            g.fillRect(getX() + level * RECTANGLE_SPACING,
                    getY() + level * RECTANGLE_SPACING,
                    getWidth() - level * RECTANGLE_SPACING * 2,
                    getHeight() - level * RECTANGLE_SPACING * 2);
        }
        g.setColor(Color.getHSBColor(
                HIT_NUMBER_HUES[hits % HIT_NUMBER_HUES.length], 1,
                1 - level * .2f));
        g.fillRect(getX() + level * RECTANGLE_SPACING,
                getY() + level * RECTANGLE_SPACING,
                getWidth() - level * RECTANGLE_SPACING * 2,
                getHeight() - level * RECTANGLE_SPACING * 2);
    }

    /**
     * @return the brick's value
     */
    public int getWorth() {
        return value;
    }

    /**
     * @return whether or not this brick should be included in the number of
     * bricks needed to count for level completion.
     */
    public boolean countsTowardLevelCompletion() {
        return hits > -1;
    }
}
