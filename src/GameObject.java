import java.awt.*;
import java.awt.geom.Area;

/**
 * Copyright 2014 Shea Polansky
 * Created for Brooke Chenoweth's Intermediate Programming course
 * Acts as the super class for all game objects, includes helper methods
 * to reduce code replication
 * Usage: None, used by Brooke's TestDriver class
 */
public abstract class GameObject implements Object2D {
    protected GameArea gameArea;
    protected Area boundingArea;

    /**
     * Constructs a new GameObject with a given GameArea and bounding shape
     * @param area the GameArea used for bounds checking
     * @param boundingArea the bounding Area of the object
     */
    public GameObject(GameArea area, Area boundingArea) {
        gameArea = area;
        this.boundingArea = boundingArea;
    }

    /**
     * Constructs a new GameObject with a given GameArea and bounding shape
     * Converts the shape to an Area object for use in collisions
     * @param area the GameArea used for bounds checking
     * @param boundingShape the bounding shape of the object
     */
    public GameObject(GameArea area, Shape boundingShape) {
        this(area, new Area(boundingShape));
    }

    /**
     * Checks if this object intersects with another
     * @param other The other object to check.
     * @return true if they intersect, false otherwise
     */
    public boolean intersects(Object2D other) {
        return getBoundingArea().intersects(other.getBoundingRectangle());
    }

    /**
     * Returns whether this object intersects another GameObject, using more
     * accurate collision testing
     * @param other the other object to test against
     * @return true if they intersect, false otherwise
     */
    public boolean intersects(GameObject other) {
        Area intersection = new Area(getBoundingArea());
        intersection.intersect(other.getBoundingArea());
        return !intersection.isEmpty();
    }

    /**
     * Returns the object's x-value based on its bounding rectangle
     * @return the x value
     */
    public int getX() {
        return (int)getBoundingRectangle().getMinX();
    }

    /**
     * Returns the object's y-value based on its bounding rectangle
     * @return the y value
     */
    public int getY() {
        return (int)getBoundingRectangle().getMinY();
    }

    /**
     * Returns the object's height based on its bounding rectangle
     * @return the object's height
     */
    public int getHeight() {
        return (int)getBoundingRectangle().getHeight();
    }

    /**
     * Returns the object's width based on its bounding rectangle
     * @return the object's width
     */
    public int getWidth() {
        return (int)getBoundingRectangle().getWidth();
    }

    /**
     * Returns the object's bounding rectangle
     * @return the bounding rectangle
     */
    public Rectangle getBoundingRectangle() {
        return boundingArea.getBounds();
    }

    /**
     * gets the bounding area of the GameObject
     * @return the bounding area
     */
    public Area getBoundingArea() {
        return boundingArea;
    }
    /**
     * Returns whether or not the object is out of the game area based on its
     * bounding rectangle
     * @return true if the object is out of bounds, false otherwise
     */
    public boolean isOutOfBounds() {
        return !(new Rectangle(0, 0, gameArea.getWidth(), gameArea.getHeight())
                .contains(getBoundingRectangle()));
    }

    /**
     * Returns the string representation of the object
     * Uses the form "ClassName at (x, y)"
     * @return the string representation of the object
     */
    public String toString() {
        return String.format("%s at (%s, %s)",
                getClass().getName(), getX(), getY());
    }
}
