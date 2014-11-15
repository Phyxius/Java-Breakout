import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Bare-bones Ball object for breakout
 * Usage: None, used by Brooke's TestDriver class
 */
public class Ball extends GameObject implements MovableObject {
    private Ellipse2D.Double ellipse;
    private int xSpeed = 0, ySpeed = 0;

    /**
     * Constructs a new Ball with given parameters
     *
     * @param x        the leftmost x value of the ball
     * @param y        the uppermost y value of the ball
     * @param diameter the diameter of the ball
     */
    public Ball(int x, int y, int diameter) {
        super(null);
        ellipse = new Ellipse2D.Double(x, y, diameter, diameter);
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
    public Area getBoundingArea() {
        return new Area(ellipse);
    }

    @Override
    public void update(UpdateManager updateManager) {
        int unclampedNewXPos = (int)ellipse.getX() + xSpeed;
        int unclampedNewYPos = (int)ellipse.getY() + ySpeed;
        int newXPos = Util.clamp(unclampedNewXPos, 0,
                (int)(updateManager.getGameAreaWidth() - ellipse.getWidth()));
        int newYPos = Util.clamp(unclampedNewYPos, 0,
                (int)(updateManager.getGameAreaHeight() - ellipse.getHeight()));
        if (newXPos != unclampedNewXPos) {
            xSpeed *= -1;
        }
        if (newYPos != unclampedNewYPos) {
            ySpeed *= -1;
        }
    }
}
