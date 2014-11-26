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
    @SuppressWarnings("FieldCanBeLocal")
    private final int RECTANGLE_SPACING = 2;
    private int hits;
    private final Rectangle rect;
    private final int value;
    private int xSpeed = 0, ySpeed = 0;
    /**
     * Constructs a new breakout.gameobjects.Brick with given parameters
     * @param x the leftmost x of the paddle
     * @param y the uppermost y of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     * @param hits the number of hits to destroy the brick
     */
    public Brick(int x, int y, int width, int height, int hits, int value) {
        this.hits = hits;
        this.value = value;
        rect = new Rectangle(x, y, width, height);
        setBoundingArea(rect);
    }
    public Brick(int x, int y, int width, int height, int hits) {
        this(x, y, width, height, hits, hits * 100);
    }
    public Brick(int x, int y, int width, int height) {
        this(x, y, width, height, 1);
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public void update(UpdateManager updateManager) {
        rect.translate(getXSpeed(), getYSpeed());
        setBoundingArea(rect);
    }

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

    @Override
    public void draw(Graphics2D g, DrawingManager manager) {
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

    public int getWorth() {
        return value;
    }
}
