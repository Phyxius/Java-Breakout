import java.awt.*;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Bare-bones brick object for breakout
 * Usage: None, used by Brooke's TestDriver class
 */
public class Brick extends GameObject {
    /**
     * Constructs a new Brick with given parameters
     * @param area the GameArea object to use for bounds checking
     * @param x the leftmost x of the paddle
     * @param y the uppermost y of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     */
    public Brick(GameArea area, int x, int y, int width, int height) {
        super(area, new Rectangle(x, y, width, height));
    }
}
