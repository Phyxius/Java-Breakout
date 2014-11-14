import java.awt.*;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Bare-bones brick object for breakout
 * Usage: None, used by Brooke's TestDriver class
 */
public class Brick extends GameObject implements MovableObject {
    private Rectangle rect;
    private int xSpeed = 0, ySpeed = 0;
    /**
     * Constructs a new Brick with given parameters
     * @param x the leftmost x of the paddle
     * @param y the uppermost y of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     */
    public Brick(int x, int y, int width, int height) {
        super();
        rect = new Rectangle(x, y, width, height);
        setBoundingArea(rect);
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
}
