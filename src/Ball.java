import java.awt.geom.Ellipse2D;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Bare-bones Ball object for breakout
 * Usage: None, used by Brooke's TestDriver class
 */
public class Ball extends GameObject {
    /**
     * Constructs a new Ball with given parameters
     * @param area the GameArea to use for bounds checking
     * @param x the leftmost x value of the ball
     * @param y the uppermost y value of the ball
     * @param diameter the diameter of the ball
     */
    public Ball(int x, int y, int diameter) {
        super(new Ellipse2D.Double(x, y, diameter, diameter));
    }
}
