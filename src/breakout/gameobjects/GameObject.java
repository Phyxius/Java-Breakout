/*
 * GameObject.java
 * Copyright (c) Shea Polansky 2014.
 * Created for Brooke Chenoweth Creel's Intermediate Programming course
 * Purpose: Superclass for game objects
 * Usage: Inherit
 */

package breakout.gameobjects;

import breakout.managers.CollisionManager;
import breakout.managers.DrawingManager;
import breakout.managers.UpdateManager;

import java.awt.*;
import java.awt.geom.Area;

public abstract class GameObject implements Object2D {
    protected Area boundingArea;

    /**
     * Constructs a new GameObject with a given breakout.GameArea and bounding shape
     * @param boundingArea the bounding Area of the object
     */
    public GameObject(Area boundingArea) {
        this.boundingArea = boundingArea;
    }
    /**
     * Constructs a new GameObject with a given breakout.GameArea and bounding shape
     * Converts the shape to an Area object for use in collisions
     * @param boundingShape the bounding shape of the object
     */
    public GameObject(Shape boundingShape) {
        this(new Area(boundingShape));
    }

    /**
     * Constructs a new GameObject with a null boundingArea
     * You MUST manually initialize the boundingArea, or override
     * getBoundingArea, or you will get NullPointerExceptions everywhere.
     */
    public GameObject() {
        boundingArea = null;
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
     * Returns whether this object intersects another breakout.gameobjects.GameObject, using more
     * accurate collision testing
     * @param other the other object to test against
     * @return true if they intersect, false otherwise
     */
    public boolean intersects(GameObject other) {
        if (!intersects((Object2D)other))
            return false;
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
        return getBoundingArea().getBounds();
    }

    /**
     * gets the bounding area of the GameObject
     * @return the bounding area
     */
    public Area getBoundingArea() {
        return boundingArea;
    }

    /**
     * Sets the boundingArea to the specified Shape
     * @param boundingArea the Shape to set the boundingArea to
     */
    protected void setBoundingArea(Shape boundingArea) {
        this.boundingArea = new Area(boundingArea);
    }

    /**
     * Updates the object by one tick. Does nothing by default.
     * @param u the UpdateManager to use to interact with the game world
     */
    public void update(UpdateManager u) {

    }

    /**
     * Draws the GameObject to the specified Graphics2D canvas
     * This method MUST have no side effects on the game world; it may be called
     * more often than the Update() method.
     * @param g the Graphics2D canvas to use
     * @param manager the DrawingManager to use to interact with the game world
     */
    public void draw(Graphics2D g, DrawingManager manager) {

    }

    /**
     * Called when this object intersects another. This method may have the side
     * effects allowed by the use of the given CollisionManager object
     * @param other the GameObject that this one collided with
     * @param manager the CollisionManager used to interact with the game world
     */
    public void onIntersect(GameObject other, CollisionManager manager) {

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
