import java.awt.*;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Bare-bones paddle object for breakout
 * Usage: None, used by Brooke's TestDriver class
 */
public class Paddle extends GameObject {
    /**
     * Constructs a new Paddle with the given parameters
     * @param x the leftmost x value of the paddle
     * @param y the leftmost y value of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     */
    public Paddle(int x, int y, int width, int height) {
        super(new Rectangle(x, y, width, height));
    }
}
