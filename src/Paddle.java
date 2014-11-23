import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Bare-bones paddle object for breakout
 * Usage: None, used by Brooke's TestDriver class
 */
public class Paddle extends GameObject {
    private Rectangle rect;
    private int moveSpeed = 10;
    /**
     * Constructs a new Paddle with the given parameters
     * @param x the leftmost x value of the paddle
     * @param y the leftmost y value of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     */
    public Paddle(int x, int y, int width, int height) {
        rect = new Rectangle(x, y, width, height);
    }

    @Override
    public Area getBoundingArea() {
        return new Area(rect);
    }

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

    @Override
    public void draw(Graphics2D g, DrawingManager manager) {
        g.setColor(Color.RED);
        g.fill(getBoundingArea());
    }
}
