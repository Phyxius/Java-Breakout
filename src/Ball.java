import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Bare-bones Ball object for breakout
 * Usage: None, used by Brooke's TestDriver class
 */
public class Ball extends GameObject implements MovableObject {
    private static final int MAX_STARTING_X_SPEED = 5,
        MAX_STARTING_Y_SPEED = 5, MIN_STARTING_X_SPEED = 3,
        MIN_STARTING_Y_SPEED = 3;
    private Ellipse2D.Double ellipse;
    private int xSpeed = 0, ySpeed = 0;

    /**
     * Constructs a new Ball with given parameters
     *
     * @param x        the center x value of the ball
     * @param y        the center y value of the ball
     * @param diameter the diameter of the ball
     */
    public Ball(int x, int y, int diameter) {
        super(null);
        ellipse = new Ellipse2D.Double(x + diameter / 2, y + diameter / 2,
                diameter, diameter);
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

    @Override
    public void onIntersect(GameObject other, CollisionManager manager) {
        if (other.getClass().equals(Paddle.class) && getYSpeed() > 0) {
            setYSpeed(-getYSpeed());
        }
        else if (other.getClass().equals(Brick.class)) {
            Brick otherBrick = (Brick)other;
            Rectangle otherRect = otherBrick.getBoundingRectangle();
            int collisionTestX = (int)(ellipse.getCenterX() +
                    Math.signum(xSpeed) * ellipse.width / 2);
            int collisionTestY = (int)(ellipse.getCenterY() +
                Math.signum(ySpeed) * ellipse.height / 2);
            if (collisionTestX < otherRect.getCenterX()) { //left of other
                xSpeed = Math.abs(xSpeed);
            }
            else if (collisionTestX >= otherRect.getCenterX()) { //right
                //allow equal to catch the corner case where it is in the
                //exact center
                xSpeed = -Math.abs(xSpeed);
            }
            else if (collisionTestY < otherRect.getCenterY()) { //above
                ySpeed = -Math.abs(ySpeed);
            }
            else if (collisionTestY >= otherRect.getCenterY()) { //below
                //see right comment
                ySpeed = Math.abs(ySpeed);
            }
            manager.modifyScore(otherBrick.getWorth());
            manager.remove(other);
            //TODO: Make bouncing actually work
        }
    }

    @Override
    public int getX() {
        return (int)getBoundingRectangle().getCenterX();
    }

    @Override
    public int getY() {
        return (int)getBoundingRectangle().getCenterY();
    }
}
